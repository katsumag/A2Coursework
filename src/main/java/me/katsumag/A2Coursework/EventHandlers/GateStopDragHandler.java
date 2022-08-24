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
import me.katsumag.A2Coursework.util.ParentHelper;
import org.girod.javafx.svgimage.SVGImage;

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
                image.relocate(event.getX(), event.getY());
                ConnectionManager connectionManager = new ComponentStore().getComponentByImage(image).getConnections();
                connectionManager.removeConnectionPoints(image);

                // TODO: needs to be done for the output as well, perhaps add to a copy of the list and then just use this?
                connectionManager.getInputs().stream().filter(connection -> connection.getConnectedLine() != null).forEach(connection -> {
                    ParentHelper parentHelper = new ParentHelper();
                    // remove connected line
                    parentHelper.removeChildFrom(image.getParent(), connection.getConnectedLine());

                    // connectedObject will stay the same, just the line needs to be redrawn

                    // connection point to be moved
                    Point2D startPoint = connection.getLocation();
                    // connection point that it's connected to
                    Bounds endPoint = connection.getConnectedObject().localToScene(connection.getConnectedObject().getBoundsInLocal());

                    // re-draw the line
                    Line newLine = new Line(startPoint.getX(), startPoint.getY(), endPoint.getCenterX(), endPoint.getCenterY());
                    connection.setConnectedLine(newLine);

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
