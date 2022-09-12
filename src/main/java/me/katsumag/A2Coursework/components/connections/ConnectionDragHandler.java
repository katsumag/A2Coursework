package me.katsumag.A2Coursework.components.connections;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import me.katsumag.A2Coursework.components.ComponentStore;
import me.katsumag.A2Coursework.util.ParentHelper;

import java.util.UUID;

public class ConnectionDragHandler implements EventHandler<MouseDragEvent> {

    /**
     * @param event the event which occurred
     */
    @Override
    public void handle(MouseDragEvent event) {

        if (!(event.getGestureSource() instanceof Circle startPoint)) { return; }
        if (!(event.getTarget() instanceof Circle endPoint)) { return; }

        if (startPoint == endPoint) { return; }

        Line line = new Line(startPoint.getCenterX(), startPoint.getCenterY(), endPoint.getCenterX(), endPoint.getCenterY());
        new ParentHelper().addChildTo(startPoint.getParent(), line);
        System.out.println("startPoint.getParent() = " + startPoint.getParent());

        // add line to starting connection point
        ComponentStore componentStore = new ComponentStore();

        // get Connections for both circles
        Connection startConnection = componentStore.getConnectionByUUID((UUID) startPoint.getProperties().get("ConnectionUUID"));
        Connection endConnection = componentStore.getConnectionByUUID((UUID) endPoint.getProperties().get("ConnectionUUID"));

        ConnectionManager startConnectionManager = componentStore.getComponentByImage(startConnection.getParentImage()).getConnections();
        ConnectionManager endConnectionManager = componentStore.getComponentByImage(endConnection.getParentImage()).getConnections();

        System.out.println("startConnectionManager = " + startConnectionManager);
        System.out.println("endConnectionManager = " + endConnectionManager);

        // Debug messages used to confirm that the connection objects in both component's
        // ConnectionManagers stay the same before and after the line connects
        // also used to confirm that connection objects are changed when the component is
        // moved, and so the connectedLine and connectedPoint properties are not transferred
        System.out.println("Start Connections Before = " + startConnectionManager.getAllConnectionPoints());

        // set moved line and point for both points
        startConnection.setConnectedLine(line);
        startConnection.setConnectedPoint(endConnection);

        System.out.println("Start Connections After = " + startConnectionManager.getAllConnectionPoints());
        System.out.println(startConnectionManager.getOutput().getConnectedLine());

        System.out.println("End Connections Before = " + endConnectionManager.getAllConnectionPoints());

        endConnection.setConnectedLine(line);
        endConnection.setConnectedPoint(startConnection);

        System.out.println("End Connections After = " + endConnectionManager.getAllConnectionPoints());
        System.out.println(endConnectionManager.getInputs().get(1).getConnectedLine());

        event.consume();

    }
}
