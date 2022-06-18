package me.katsumag.A2Coursework;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import me.katsumag.A2Coursework.util.DragUtil;

public class GateDragMovementHandler implements EventHandler<DragEvent> {


    // acceptTransferMode if the targeted object is whereever you want to be able to drop the source
    // gestureTarget is always null for whatever reason
    // From the docs:
    // "The target object of the drag and drop gesture. Gesture target is the object that accepts drag events.
    // The value null is valid in the case that the drag and drop gesture has been canceled or completed without a
    // transfer taking place or there is currently no event target accepting the drag events."
    @Override
    public void handle(DragEvent event) {
        // TODO: show gate over mouse cursor
        System.out.println("Dragging");

        // check the gate is not dragged back onto the same panel it left
        System.out.println("event.getGestureSource() = " + event.getGestureSource());
        System.out.println("event.getGestureTarget() = " + event.getGestureTarget());
        if (event.getGestureSource() == event.getGestureTarget()) return;

        if (DragUtil.isCenterPanel(event.getGestureTarget())) {
            System.out.println("Allow dropping here");
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

}
