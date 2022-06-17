package me.katsumag.A2Coursework;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class GateDragMovementHandler implements EventHandler<DragEvent> {

    @Override
    public void handle(DragEvent event) {
        // TODO: show gate over mouse cursor
        System.out.println("Dragging");

        // check the gate is not dragged back onto the same panel it left
        if (event.getGestureSource() == event.getGestureTarget()) return;

        if (! (event.getGestureTarget() instanceof Node)) return;

        Node node = (Node) event.getGestureTarget();

        // check to see if the drag target is the center panel
        if (! node.getProperties().containsKey("CenterPanel")) return;

        // probably unnecessary checks, but doesn't hurt anyway. Ensures CenterPanel is true
        if (! (node.getProperties().get("CenterPanel") instanceof Boolean)) return;
        if (! ((Boolean)(node.getProperties().get("CenterPanel")))) return;

        // allow for copying
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

        event.consume();
    }

}
