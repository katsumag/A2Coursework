package me.katsumag.A2Coursework.components.connections;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import me.katsumag.A2Coursework.components.ComponentStore;
import me.katsumag.A2Coursework.util.ParentHelper;

import java.util.UUID;

public class ConnectionDragDroppedHandler implements EventHandler<DragEvent> {

    /**
     * @param event the event which occurred
     */
    @Override
    public void handle(DragEvent event) {

        System.out.println("Dropped");

        if (!(event.getGestureSource() instanceof Circle startPoint)) { return; }
        if (!(event.getGestureTarget() instanceof Circle endPoint)) { return; }

        if (startPoint == endPoint) { return; }

        event.setDropCompleted(true);

        event.consume();

    }
}
