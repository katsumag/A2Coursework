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

        System.out.println("event.getSource() = " + event.getSource());
        System.out.println("event.getTarget() = " + event.getTarget());

        if (!(event.getSource() instanceof Circle startPoint)) { return; }

        // allow mouse events to be delivered to underlying nodes
        startPoint.startFullDrag();
        System.out.println("Full drag started");

        event.consume();

    }
}
