package me.katsumag.A2Coursework.components.connections;

import javafx.event.EventHandler;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import me.katsumag.A2Coursework.components.ComponentStore;
import me.katsumag.A2Coursework.util.ParentHelper;

import java.util.UUID;

/**
  * Deals with creating a line and connecting components together
  * Occurs when a drag and drop gesture between connection points ends
 */
public class ConnectionDragDroppedHandler implements EventHandler<MouseDragEvent> {

    /**
      * @param event the event which occurred
     */
    @Override
    public void handle(MouseDragEvent event) {

        // Check both sides of the drag and drop gesture are connection points
        if (!(event.getGestureSource() instanceof Circle startPoint)) { return; }
        if (!(event.getTarget() instanceof Circle endPoint)) { return; }

        // Checks that a connection point hasn't been dropped onto itself
        if (startPoint == endPoint) { return; }

        // Creates a line between the connection points, and makes it visible
        Line line = new Line(startPoint.getCenterX(), startPoint.getCenterY(), endPoint.getCenterX(), endPoint.getCenterY());
        new ParentHelper().addChildTo(startPoint.getParent(), line);

        // add line to starting connection point
        ComponentStore componentStore = new ComponentStore();

        // get Connections for both circles
        Connection startConnection = componentStore.getConnectionByUUID((UUID) startPoint.getProperties().get("ConnectionUUID"));
        Connection endConnection = componentStore.getConnectionByUUID((UUID) endPoint.getProperties().get("ConnectionUUID"));

        // set moved line and point for both points
        startConnection.setConnectedLine(line);
        startConnection.setConnectedPoint(endConnection);

        endConnection.setConnectedLine(line);
        endConnection.setConnectedPoint(startConnection);

        event.consume();

    }
}
