package me.katsumag.A2Coursework.components.connections;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/** Deals with starting a full drag and drop gesture when one is detected */
public class ConnectionDragDetectedHandler implements EventHandler<MouseEvent> {

    /** Starts a full drag and drop gesture when dragging from a connection point */
    @Override
    public void handle(MouseEvent event) {

        if (!(event.getSource() instanceof Circle startPoint)) { return; }

        // allow mouse events to be delivered to underlying nodes
        startPoint.startFullDrag();

        event.consume();

    }
}
