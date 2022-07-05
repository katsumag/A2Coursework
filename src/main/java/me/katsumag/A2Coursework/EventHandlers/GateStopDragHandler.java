package me.katsumag.A2Coursework.EventHandlers;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import me.katsumag.A2Coursework.components.*;
import me.katsumag.A2Coursework.components.connections.ConnectionManager;
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
                image.relocate(event.getX(), event.getY());
                new ComponentStore().getComponentByImage(image).getConnections().removeConnectionPoints(image);

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

    }

}
