package me.katsumag.A2Coursework.components.connections;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.shape.Circle;

public class ConnectionDragDroppedHandler implements EventHandler<DragEvent> {

    /**
     * @param event the event which occurred
     */
    @Override
    public void handle(DragEvent event) {

        if (!(event.getGestureSource() instanceof Circle startPoint)) { return; }
        if (!(event.getGestureTarget() instanceof Circle endPoint)) { return; }

        if (startPoint == endPoint) { return; }

        event.setDropCompleted(true);

        event.consume();

    }
}
