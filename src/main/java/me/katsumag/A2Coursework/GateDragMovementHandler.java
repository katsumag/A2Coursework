package me.katsumag.A2Coursework;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import org.girod.javafx.svgimage.SVGImage;

/**
 * Called when a {@link me.katsumag.A2Coursework.components.CircuitComponent} is dragged over the center pane.
 */
public class GateDragMovementHandler implements EventHandler<DragEvent> {

    @Override
    public void handle(DragEvent event) {
        // TODO: show gate over mouse cursor

        // Check that the source isn't being dragged onto itself
        if (event.getGestureSource() == event.getGestureTarget()) return;

        // Check the source is one of my images
        if (! (event.getGestureSource() instanceof SVGImage)) return;
        SVGImage source = ((SVGImage) event.getGestureSource());

        // Check that it has the property needed to establish its type later on
        if (! source.getProperties().containsKey("CircuitComponentType")) return;

        // Mark that the target will accept the drag and drop gesture
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

        event.consume();
    }

}
