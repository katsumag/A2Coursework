package me.katsumag.A2Coursework;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class GateDragMovementHandler implements EventHandler<DragEvent> {

    @Override
    public void handle(DragEvent event) {
        // TODO: show gate over mouse cursor
        System.out.println("Dragging");

        // check the gate is not dragged back onto the same panel it left
        if (event.getGestureSource() == event.getGestureTarget()) return;

        // allow for copying
        event.acceptTransferModes(TransferMode.COPY);

        event.consume();
    }

}
