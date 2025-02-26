package edu.wpi.grip.core.util.service;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Many of these mock service objects are copied from Guava's test framework.
 *
 * @see <a href="https://github.com/google/guava/blob/a9f8b899c07a33c2203b4e6cf84861646952aeed/guava-tests/test/com/google/common/util/concurrent/AbstractExecutionThreadServiceTest.java">Original
 *     Guava Tests</a>
 */
public class AutoRestartingServiceTest {
  private CountDownLatch enterRun;
  private CountDownLatch exitRun;
  private Thread executionThread;
  private Throwable thrownByExecutionThread;
  private Executor exceptionCatchingExecutor;

  @SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD",
      justification = "A JUnit rule -- used by JUnit")
  @Rule
  public Timeout timeout = new Timeout(10, TimeUnit.SECONDS);

  private static final Logger logger = Logger.getLogger(AutoRestartingServiceTest.class.getName());

  @Before
  public void setUp() {
    // Make sure that this is always reset
    thrownByExecutionThread = null;

    resetCounterLatches();
    exceptionCatchingExecutor = command -> {
      executionThread = new Thread(command, "Exception Catching Executor");
      executionThread.setUncaughtExceptionHandler((thread, e) -> thrownByExecutionThread = e);
      executionThread.setDaemon(true);
      executionThread.start();
    };
  }

  private void resetCounterLatches() {
    enterRun = new CountDownLatch(1);
    exitRun = new CountDownLatch(1);
  }

  @After
  public void tearDown() {
    assertNull("exceptions should not be propagated to uncaught exception handlers",
        thrownByExecutionThread);
  }

  @Test
  public void testStartStopMultipleTimes() throws InterruptedException {
    final RecordingSupplier<WaitOnRunService> recordingSupplier =
        new RecordingSupplier<>(WaitOnRunService::new);
    final AutoRestartingService<WaitOnRunService> restartingService =
        new AutoRestartingService<>(recordingSupplier, ServiceRestartPolicy.IMMEDIATE);

    assertFalse("Start should not have been called", restartingService.getDelegate().startUpCalled);

    final Consumer<String> runner = runName -> {
      final String messagePrefix = runName + ": ";
      try {
        resetCounterLatches();

        restartingService.startAsync().awaitRunning();
        assertTrue(messagePrefix + "startUp was not called", restartingService.getDelegate()
            .startUpCalled);
        assertEquals(messagePrefix + "State was not running", Service.State.RUNNING,
            restartingService.getDelegate().state());

        enterRun.await(); // to avoid stopping the service until run() is invoked

        restartingService.stopAsync().awaitTerminated();

        assertTrue(messagePrefix + "shutDown was not called", restartingService.getDelegate()
            .shutDownCalled);
        assertEquals(messagePrefix + "State was not terminated", Service.State.TERMINATED,
            restartingService.getDelegate().state());
        executionThread.join();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    };

    runner.accept("First");
    runner.accept("Second");
    runner.accept("Third");
    runner.accept("Fourth");
    assertThat(recordingSupplier.services).hasSize(4);
  }

  @Test
  public void testCallStartTwiceFails() throws InterruptedException {
    final RecordingSupplier<WaitOnRunService> recordingSupplier =
        new RecordingSupplier<>(WaitOnRunService::new);
    final AutoRestartingService<WaitOnRunService> restartingService =
        new AutoRestartingService<>(recordingSupplier, ServiceRestartPolicy.IMMEDIATE);

    restartingService.startAsync();
    try {
      restartingService.startAsync();
      fail("Should have thrown an IllegalStateException");
    } catch (IllegalStateException e) {
      logger.log(Level.OFF, e.getMessage(), e);
    }

    restartingService.awaitRunning();
    enterRun.await();

    restartingService.stopAsync().awaitTerminated();
    executionThread.join();

    assertEquals("Supplier should only be called once if start is called twice", 1,
        recordingSupplier.services.size());
  }

  @Test
  public void testServiceStaysRunningIfThrowOnRunning() throws InterruptedException {
    final RecordingSupplier<WaitThenThrowOnRunService> recordingSupplier =
        new RecordingSupplier<>(WaitThenThrowOnRunService::new);
    final AutoRestartingService<WaitThenThrowOnRunService> restartingService =
        new AutoRestartingService<>(recordingSupplier, ServiceRestartPolicy.IMMEDIATE);

    @SuppressWarnings("PMD.PrematureDeclaration") final Service initialDelegate
        = restartingService.getDelegate();

    try {
      // This waits for this instance of the service to be terminated
      restartingService.startAsync();
      restartingService.getDelegate().runLatch.countDown();
      restartingService.stopAndAwait();
      // This may or may not throw an exception depending on the state of the service
    } catch (IllegalStateException expected) {
      // But if it does we want to make sure that it is the right exception
      if (expected.getCause() == null) {
        throw expected;
      }
      assertThat(expected.getCause()).hasMessageThat().contains("kaboom!");
    }

    try {
      restartingService.startAsync();
      restartingService.getDelegate().runLatch.countDown();
      restartingService.stopAndAwait();
      // This may or may not throw an exception depending on the state of the service
    } catch (IllegalStateException expected) {
      // But if it does we want to make sure that it is the right exception
      if (expected.getCause() == null) {
        throw expected;
      }
      assertThat(expected.getCause()).hasMessageThat().contains("kaboom!");
    }

    assertNotEquals("A new service should be created when the old service terminated",
        initialDelegate, restartingService.getDelegate());
  }

  @Test
  public void testListenerGetsAddedToRunningService() throws InterruptedException {
    final RecordingSupplier<WaitOnRunService> recordingSupplier =
        new RecordingSupplier<>(WaitOnRunService::new);
    final AutoRestartingService<WaitOnRunService> restartingService =
        new AutoRestartingService<>(recordingSupplier, ServiceRestartPolicy.IMMEDIATE);
    final boolean[] startingListener = {false};
    final boolean[] stoppingListener = {false};


    restartingService.addListener(new Service.Listener() {
      @Override
      public void starting() {
        startingListener[0] = true;
      }
    }, MoreExecutors.directExecutor());

    restartingService.startAsync().awaitRunning();
    assertTrue("starting listener should be called", startingListener[0]);

    restartingService.addListener(new Service.Listener() {
      @Override
      public void stopping(Service.State from) {
        stoppingListener[0] = true;
      }
    }, MoreExecutors.directExecutor());
    assertFalse(stoppingListener[0]);

    enterRun.await();

    restartingService.stopAndAwait();

    assertTrue("starting listener was not called", startingListener[0]);
    assertTrue("stopping listener was not called", stoppingListener[0]);

  }

  /**
   * Records all instances of the object returned by {@link Supplier#get()}
   */
  private static class RecordingSupplier<S extends Service> implements Supplier<S> {
    private final List<S> services = new LinkedList<>();
    private final Supplier<S> serviceSupplier;

    private RecordingSupplier(Supplier<S> serviceSupplier) {
      this.serviceSupplier = serviceSupplier;
    }

    @Override
    public S get() {
      final S instance = serviceSupplier.get();
      services.add(instance);
      return instance;
    }
  }

  private class WaitOnRunService extends AbstractExecutionThreadService {
    private boolean startUpCalled = false;
    private boolean runCalled = false;
    private boolean shutDownCalled = false;
    private final State expectedShutdownState = State.STOPPING;

    @Override
    protected void startUp() {
      assertFalse("start up was already called", startUpCalled);
      assertFalse("run was already called", runCalled);
      assertFalse("shut down was already called", shutDownCalled);
      startUpCalled = true;
      assertEquals("State was not starting", State.STARTING, state());
    }

    @Override
    protected void run() {
      assertTrue("start up was not called", startUpCalled);
      assertFalse("run was already called", runCalled);
      assertFalse("shutdown was already called", shutDownCalled);
      runCalled = true;
      assertEquals("State was not running", State.RUNNING, state());

      enterRun.countDown();
      try {
        exitRun.await();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    protected void shutDown() {
      assertTrue("start up was not called", startUpCalled);
      assertTrue("run was not already called", runCalled);
      assertFalse("shut down was already called", shutDownCalled);
      shutDownCalled = true;
      assertEquals("State was not shutting down", expectedShutdownState, state());
    }

    @Override
    protected void triggerShutdown() {
      exitRun.countDown();
    }

    @Override
    protected Executor executor() {
      return exceptionCatchingExecutor;
    }
  }

  private class WaitThenThrowOnRunService extends AbstractExecutionThreadService {
    @SuppressWarnings("PMD.FinalFieldCouldBeStatic")
    private final CountDownLatch runLatch = new CountDownLatch(1);

    @Override
    protected void run() throws InterruptedException {
      runLatch.await();
      throw new UnsupportedOperationException("kaboom!");
    }

    @Override
    protected Executor executor() {
      return exceptionCatchingExecutor;
    }
  }
}
