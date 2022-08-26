package me.katsumag.A2Coursework.components.connections;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class ConnectionDragDetectedHandler implements EventHandler<MouseEvent> {

    /**
     * @param event
     */
    @Override
    public void handle(MouseEvent event) {

        if (!(event.getSource() instanceof Circle startPoint)) { return; }

        // allow mouse events to be delivered to underlying nodes
        startPoint.startFullDrag();

        event.consume();

    }
}
