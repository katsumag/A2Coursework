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
  * Handles hiding any visible connection points for a component when it is no longer hovered over
 */
public class ConnectionPointHideHandler implements EventHandler<MouseEvent> {

    /**
      * Called when the user takes their cursor off a placed component
      * Removes the component's connection points from the screen
     */
    @Override
    public void handle(MouseEvent event) {

        // fixes #12
        if (!(event.getButton() == MouseButton.NONE)) { return; }

        if (! (event.getSource() instanceof SVGImage image)) { return; }

        // Image is still on left pane
        if (image.getParent() instanceof VBox) { return; }

        // Don't hide connection points if the user is hovering over one
        if (event.getPickResult().getIntersectedNode() instanceof Circle) { return; }

        // Hide connection points
        CircuitComponent component = new ComponentStore().getComponentByUUID((UUID) image.getProperties().get("ComponentUUID"));
        component.getConnections().hideConnectionPoints(image);
        event.consume();
    }
}
