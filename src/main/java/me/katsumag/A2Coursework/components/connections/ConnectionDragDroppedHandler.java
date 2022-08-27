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

        System.out.println("Add line");

        Line line = new Line(startPoint.getLayoutX(), startPoint.getLayoutY(), endPoint.getLayoutX(), endPoint.getLayoutY());
        new ParentHelper().addChildTo(startPoint.getParent(), line);

        // add line to starting connection point
        ComponentStore componentStore = new ComponentStore();

        // get Connections for both circles
        Connection startConnection = componentStore.getConnectionByUUID((UUID) startPoint.getProperties().get("ConnectionUUID"));
        Connection endConnection = componentStore.getConnectionByUUID((UUID) endPoint.getProperties().get("ConnectionUUID"));

        System.out.println("Dropped image connections= " + componentStore.getComponentByUUID((UUID) endConnection.getParentImage().getProperties().get("UUID")).getConnections().getAllConnectionPoints());
        System.out.println("after");
        // set moved line and point for both points
        startConnection.setConnectedLine(line);
        startConnection.setConnectedPoint(endConnection);

        endConnection.setConnectedLine(line);
        endConnection.setConnectedPoint(startConnection);

        event.setDropCompleted(true);

        event.consume();

    }
}
