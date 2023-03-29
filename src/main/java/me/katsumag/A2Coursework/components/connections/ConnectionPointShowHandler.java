package me.katsumag.A2Coursework.components.connections;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import me.katsumag.A2Coursework.components.CircuitComponent;
import me.katsumag.A2Coursework.components.ComponentStore;
import org.girod.javafx.svgimage.SVGImage;

import java.util.UUID;

/**
  * Handles showing a component's connection points when it is hovered over
 */
public class ConnectionPointShowHandler implements EventHandler<MouseEvent> {

    /**
      * Called when a component is hovered over
      * Adds its existing connection points to the screen
     */
    @Override
    public void handle(MouseEvent event) {

        // Fixes #12 + allows for this listener to be used for onMouseDragEnter
        // This is because, for whatever reason, there are no buttons pressed during this drag event
        if (!(event.getButton() == MouseButton.NONE) && !(event.isPrimaryButtonDown())) { return; }

        if (! (event.getSource() instanceof SVGImage image)) { return; }

        // Image is still on left pane
        if (image.getParent() instanceof VBox) { return; }

        // Don't show connection points if they're already there
        if (event.getPickResult().getIntersectedNode() instanceof Circle) { return; }

        // Show connection points
        CircuitComponent component = new ComponentStore().getComponentByUUID((UUID) image.getProperties().get("ComponentUUID"));

        component.getConnections().showConnectionPoints(image);
        event.consume();
    }
}
