package me.katsumag.A2Coursework;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import me.katsumag.A2Coursework.components.*;
import org.girod.javafx.svgimage.SVGImage;

public class GateStopDragHandler implements EventHandler<DragEvent> {

    // https://docs.oracle.com/javafx/2/drag_drop/HelloDragAndDrop.java.html
    // TODO: one of many problems, newComponent won't be visible because it's not added to the centre panel

    @Override
    public void handle(DragEvent event) {

        if (! (event.getGestureSource() instanceof SVGImage)) return;
        SVGImage image = ((SVGImage) event.getGestureSource());
        if (! image.getProperties().containsKey("CircuitComponentType")) return;
        CircuitComponentType type = CircuitComponentType.valueOf((String) image.getProperties().get("CircuitComponentType"));

        CircuitComponent newComponent;

        switch (type) {
            case AND:
                newComponent = new ANDGate();
            case OR:
                newComponent = new ORGate();
            case NOT:
                newComponent = new NOTGate();
            default:
                newComponent = new NOTGate();
        }

        newComponent.getImage().relocate(event.getSceneX(), event.getScreenY());

        event.setDropCompleted(true);

    }

}
