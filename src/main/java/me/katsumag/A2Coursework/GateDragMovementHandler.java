package me.katsumag.A2Coursework;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import me.katsumag.A2Coursework.util.DragUtil;

public class GateDragMovementHandler implements EventHandler<DragEvent> {

    @Override
    public void handle(DragEvent event) {
        // TODO: show gate over mouse cursor
        System.out.println("Dragging");

        // check the gate is not dragged back onto the same panel it left
        if (event.getGestureSource() == event.getGestureTarget()) return;

        if (DragUtil.isCenterPanel(event.getGestureTarget())) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

}
