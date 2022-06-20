package me.katsumag.A2Coursework;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.TilePane;
import me.katsumag.A2Coursework.components.*;
import org.girod.javafx.svgimage.SVGImage;

public class GateStopDragHandler implements EventHandler<DragEvent> {

    // https://docs.oracle.com/javafx/2/drag_drop/HelloDragAndDrop.java.html

    @Override
    public void handle(DragEvent event) {

        // Establish that the dropped object was a useable SVGImage
        if (! (event.getGestureSource() instanceof SVGImage)) return;
        SVGImage image = ((SVGImage) event.getGestureSource());
        if (! image.getProperties().containsKey("CircuitComponentType")) return;

        // Add debug message to see if the ComponentType is always NOT
        System.out.println(image.getProperties().get("CircuitComponentType"));

        // No check is required for the gesture target, as this event handler can only be called by the center pane

        // The get the type of image to create
        CircuitComponentType type = CircuitComponentType.valueOf((String) image.getProperties().get("CircuitComponentType"));

        CircuitComponent newComponent;

        switch (type) {
            case AND:
                newComponent = new ANDGate();
            case OR:
                newComponent = new ORGate();
            case NOT:
                newComponent = new NOTGate();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        newComponent.getImage().relocate(event.getSceneX(), event.getScreenY());

        // I know that the target will be a TilePane (for now, anyway) as that's the type our center panel is
        ((TilePane) event.getGestureTarget()).getChildren().add(newComponent.getImage());

        event.setDropCompleted(true);

    }

}
