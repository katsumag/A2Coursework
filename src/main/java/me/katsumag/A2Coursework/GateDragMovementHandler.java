package me.katsumag.A2Coursework;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import me.katsumag.A2Coursework.components.CircuitComponent;
import me.katsumag.A2Coursework.components.CircuitComponentType;
import me.katsumag.A2Coursework.util.DragUtil;
import org.girod.javafx.svgimage.SVGImage;

public class GateDragMovementHandler implements EventHandler<DragEvent> {

    @Override
    public void handle(DragEvent event) {
        // TODO: show gate over mouse cursor

        if (event.getGestureSource() == event.getGestureTarget()) return;

        if (! (event.getGestureSource() instanceof SVGImage)) return;
        SVGImage source = ((SVGImage) event.getGestureSource());

        if (! source.getProperties().containsKey("CircuitComponentType")) return;

        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

        event.consume();
    }

}
