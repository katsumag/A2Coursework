package me.katsumag.A2Coursework.EventHandlers;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import me.katsumag.A2Coursework.components.*;
import me.katsumag.A2Coursework.components.connections.Connection;
import me.katsumag.A2Coursework.components.connections.ConnectionManager;
import me.katsumag.A2Coursework.util.BoundsHelper;
import me.katsumag.A2Coursework.util.ParentHelper;
import org.girod.javafx.svgimage.SVGImage;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GateStopDragHandler implements EventHandler<DragEvent> {

    // https://docs.oracle.com/javafx/2/drag_drop/HelloDragAndDrop.java.html

    @Override
    public void handle(DragEvent event) {

        // Establish that the dropped object was a useable SVGImage
        if (!(event.getGestureSource() instanceof SVGImage image)) return;
        if (!image.getProperties().containsKey("CircuitComponentType")) return;

        // Handle drag and drop movements inside the center pane to relocate a component
        // Establish that the component wasn't dragged from the VBox of the left BorderPane
        // No check is required for the gesture target, as this event handler can only be called by the center pane
        if (!(image.getParent() instanceof VBox)) {
            // Establish that the component is assigned to the center Pane
            // Must be checked in this order as VBox extends Pane
            if (image.getParent() instanceof Pane) {
                // relocate the image and remove the old connection points
                System.out.println("moved");

                ConnectionManager connectionManager = new ComponentStore().getComponentByImage(image).getConnections();
                System.out.println("Output location before = " + connectionManager.getOutput().getLocation());

                image.relocate(event.getX(), event.getY());

                System.out.println("connectionManager = " + connectionManager);

                List<Connection> rawConnections = connectionManager.getAllConnectionPoints();
                List<Connection> connectionsToProcess = rawConnections.stream().filter(connection -> connection != null && connection.getConnectedLine() != null).toList();
                System.out.println("All Connection Points = " + rawConnections);

                System.out.println("Connections to process = " + connectionsToProcess);

                connectionManager.refreshConnectionPoints(image);

                System.out.println("Output location after = " + connectionManager.getOutput().getLocation());

                // main logic - remove and replace the old line
                connectionsToProcess.forEach(connection -> {
                    ParentHelper parentHelper = new ParentHelper();

                    Line line = connection.getConnectedLine();
                    System.out.println("connection.getConnectedLine() = " + line);

                    // connectedPoint and parentImage will stay the same, just the line needs to be redrawn
                    parentHelper.removeChildFrom(line.getParent(), line);

                    // connection point to be moved
                    Point2D startPoint = connection.getLocation();

                    // connection point that it's connected to
                    Point2D endPoint = connection.getConnectedPoint().getLocation();

                    // re-draw the line
                    Line newLine = new Line(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
                    System.out.println("newLine = " + newLine);
                    connection.setConnectedLine(newLine);
                    connection.getConnectedPoint().setConnectedLine(newLine);

                    // add to screen
                    parentHelper.addChildTo(image.getParent(), newLine);

                });

                // Stop execution here so that a new component isn't added.
                return;
            }
        }

        // The get the type of image to create
        CircuitComponentType type = CircuitComponentType.valueOf((String) image.getProperties().get("CircuitComponentType"));

        CircuitComponent newComponent = switch (type) {
            case AND -> new ANDGate();
            case OR -> new ORGate();
            case NOT -> new NOTGate();
            case SWITCH -> new Switch();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };

        newComponent.getImage().relocate(event.getX(), event.getY());

        // I know that the target will be a Pane as that's the type our center panel is
        ((Pane) event.getGestureTarget()).getChildren().add(newComponent.getImage());

        event.setDropCompleted(true);

        event.consume();

    }

}
