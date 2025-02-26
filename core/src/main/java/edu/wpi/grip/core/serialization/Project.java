package edu.wpi.grip.core.serialization;

import edu.wpi.grip.annotation.processor.ClassListProcessor;
import edu.wpi.grip.core.Pipeline;
import edu.wpi.grip.core.PipelineRunner;
import edu.wpi.grip.core.events.DirtiesSaveEvent;
import edu.wpi.grip.core.events.WarningEvent;
import edu.wpi.grip.core.util.MetaInfReader;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Helper for saving and loading a processing pipeline to and from a file.
 */
@Singleton
public class Project {

  protected final XStream xstream = new XStream(new PureJavaReflectionProvider());
  @Inject
  private EventBus eventBus;
  @Inject
  private Pipeline pipeline;
  @Inject
  private PipelineRunner pipelineRunner;
  private Optional<File> file = Optional.empty();
  private final ObservableBoolean saveIsDirty = new ObservableBoolean();

  @Inject
  public void initialize(StepConverter stepConverter,
                         SourceConverter sourceConverter,
                         SocketConverter socketConverter,
                         ConnectionConverter connectionConverter,
                         ProjectSettingsConverter projectSettingsConverter,
                         CodeGenerationSettingsConverter codeGenerationSettingsConverter) {
    xstream.setMode(XStream.NO_REFERENCES);
    xstream.ignoreUnknownElements(); // ignores all unknown tags
    xstream.registerConverter(stepConverter);
    xstream.registerConverter(sourceConverter);
    xstream.registerConverter(socketConverter);
    xstream.registerConverter(connectionConverter);
    xstream.registerConverter(projectSettingsConverter);
    xstream.registerConverter(codeGenerationSettingsConverter);
    try {
      MetaInfReader.readClasses(ClassListProcessor.XSTREAM_ALIASES_FILE_NAME)
          .forEach(xstream::processAnnotations);
    } catch (IOException ex) {
      throw new AssertionError("Could not load classes for XStream annotation processing", ex);
    }
  }

  /**
   * @return The file that this project is located in, if it was loaded from/saved to a file.
   */
  public Optional<File> getFile() {
    return file;
  }

  public void setFile(Optional<File> file) {
    this.file = file;
  }

  /**
   * Load the project from a file.
   *
   * @throws IOException if the project file could not be read
   */
  public void open(File file) throws IOException {
    try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file),
        StandardCharsets.UTF_8)) {
      this.open(reader);
    }
    this.file = Optional.of(file);
  }

  /**
   * Loads the project defined by the given XML string. This is intended to be used to be able to
   * programmatically run a pipeline from a remote source. Therefore, this does <strong>not</strong>
   * save the contents to disk; if it is called in GUI mode, the user will have to manually save the
   * file.
   *
   * @param projectXml the XML string defining the project to open
   */
  public void open(String projectXml) {
    open(new StringReader(projectXml));
  }

  @VisibleForTesting
  void open(Reader reader) {
    pipelineRunner.stopAndAwait();
    this.pipeline.clear();
    this.xstream.fromXML(reader);
    pipelineRunner.startAsync();
    saveIsDirty.set(false);
  }

  /**
   * Tries to save this project to the given file. Unlike {@link #save(File)}, this will <i>not</i>
   * throw an IOException and will instead post a warning event to the event bus.
   *
   * @param file the file to save to
   * @return true if the project was successfully saved to the given file, or false if the file
   *     could not be written to
   */
  public boolean trySave(File file) {
    try {
      save(file);
      return true;
    } catch (IOException e) {
      eventBus.post(new WarningEvent(
          "Could not save project",
          "The project could not be saved to " + file.getAbsolutePath()
              + ".\n\nCause: " + e.getMessage()
      ));
      return false;
    }
  }

  /**
   * Save the project to a file.
   *
   * @throws IOException if the file could not written
   */
  public void save(File file) throws IOException {
    try (Writer writer = new OutputStreamWriter(new FileOutputStream(file),
        StandardCharsets.UTF_8)) {
      this.save(writer);
    }
    this.file = Optional.of(file);
  }

  public void save(Writer writer) {
    this.xstream.toXML(this.pipeline, writer);
    saveIsDirty.set(false);
  }

  public boolean isSaveDirty() {
    return saveIsDirty.get();
  }

  public void addIsSaveDirtyConsumer(Consumer<Boolean> consumer) {
    saveIsDirty.addConsumer(consumer);
  }

  @Subscribe
  public void onDirtiesSaveEvent(DirtiesSaveEvent dirtySaveEvent) {
    // Only update the flag the save isn't already dirty
    // We don't need to be redundantly checking if the event dirties the save
    if (!saveIsDirty.get() && dirtySaveEvent.doesDirtySave()) {
      saveIsDirty.set(true);
    }
  }

  private static final class ObservableBoolean {

    private boolean b = false;
    private final List<Consumer<Boolean>> consumers = new LinkedList<>();

    public void set(boolean b) {
      this.b = b;
      consumers.parallelStream().forEach(c -> c.accept(b));
    }

    @SuppressWarnings("PMD.BooleanGetMethodName")
    public boolean get() {
      return b;
    }

    public void addConsumer(Consumer<Boolean> consumer) {
      consumers.add(consumer);
    }
  }
}
