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

        Line line = new Line(startPoint.getLayoutX(), startPoint.getLayoutY(), endPoint.getLayoutX(), endPoint.getLayoutY());
        new ParentHelper().addChildTo(startPoint.getParent(), line);

        // add line to starting connection point
        //TODO: connectedObject and connectedLine need to be set on both startPoint and endPoint for use when the component is moved
        Connection connection = new ComponentStore().getConnectionByUUID((UUID) startPoint.getProperties().get("ConnectionUUID"));
        connection.setConnectedLine(line);
        connection.setConnectedObject()

        event.setDropCompleted(true);

        event.consume();

    }
}
