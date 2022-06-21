package me.katsumag.A2Coursework.EventHandlers;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import me.katsumag.A2Coursework.components.*;
import org.girod.javafx.svgimage.SVGImage;

public class GateStopDragHandler implements EventHandler<DragEvent> {

    // https://docs.oracle.com/javafx/2/drag_drop/HelloDragAndDrop.java.html

    @Override
    public void handle(DragEvent event) {

        // Establish that the dropped object was a useable SVGImage
        if (!(event.getGestureSource() instanceof SVGImage image)) return;
        if (!image.getProperties().containsKey("CircuitComponentType")) return;

        // No check is required for the gesture target, as this event handler can only be called by the center pane

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

        // I know that the target will be a TilePane (for now, anyway) as that's the type our center panel is
        ((Pane) event.getGestureTarget()).getChildren().add(newComponent.getImage());

        event.setDropCompleted(true);

    }

}
