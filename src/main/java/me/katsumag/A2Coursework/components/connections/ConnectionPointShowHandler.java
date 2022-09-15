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

public class ConnectionPointShowHandler implements EventHandler<MouseEvent> {

    /**
     * @param event the event which occurred
     */
    @Override
    public void handle(MouseEvent event) {

        // fixes #12 + allows for this listener to be used for onMouseDragEnter
        // This is because, for whatever reason, there are no buttons pressed during this drag event
        if (!(event.getButton() == MouseButton.NONE) && !(event.isPrimaryButtonDown())) { return; }

        if (! (event.getSource() instanceof SVGImage image)) { return; }

        // image is still on left pane
        if (image.getParent() instanceof VBox) { return; }

        if (event.getPickResult().getIntersectedNode() instanceof Circle) { return; }

        CircuitComponent component = new ComponentStore().getComponentByUUID((UUID) image.getProperties().get("ComponentUUID"));

        component.getConnections().showConnectionPoints(image);
        event.consume();
    }
}
