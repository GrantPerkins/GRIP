package edu.wpi.grip.ui.preview;

import edu.wpi.grip.core.events.RenderEvent;
import edu.wpi.grip.core.sockets.OutputSocket;
import edu.wpi.grip.ui.util.GripPlatform;

import com.google.common.eventbus.Subscribe;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Size;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * A {@link SocketPreviewView} for OpenCV points and sizes.
 */
public class PointSizeSocketPreviewView<T extends IntPointer> extends SocketPreviewView<T> {

  private final TextField x;
  private final TextField y;
  private final GripPlatform platform;

  /**
   * @param socket An output socket to preview.
   */
  PointSizeSocketPreviewView(GripPlatform platform, OutputSocket<T> socket) {
    super(socket);
    this.platform = platform;

    x = new TextField();
    x.setEditable(false);
    y = new TextField();
    y.setEditable(false);

    final GridPane gridPane = new GridPane();
    gridPane.add(x, 1, 0);
    gridPane.add(y, 1, 1);

    // The only difference between point and size previews is the labels
    if (socket.getSocketHint().getType().equals(Point.class)) {
      gridPane.add(new Label("x: "), 0, 0);
      gridPane.add(new Label("y: "), 0, 1);
    } else if (socket.getSocketHint().getType().equals(Size.class)) {
      gridPane.add(new Label("width: "), 0, 0);
      gridPane.add(new Label("height: "), 0, 1);
    }
    this.setContent(gridPane);

    assert Platform.isFxApplicationThread() : "Must be in FX Thread to create this or you will be"
        + " exposing constructor to another thread!";
    updateTextFields();
  }

  @Subscribe
  public void onRenderEvent(RenderEvent event) {
    this.updateTextFields();
  }

  private void updateTextFields() {
    platform.runAsSoonAsPossible(() -> {
      this.x.setText(Integer.toString(this.getSocket().getValue().get().get(0)));
      this.y.setText(Integer.toString(this.getSocket().getValue().get().get(1)));
    });
  }
}
