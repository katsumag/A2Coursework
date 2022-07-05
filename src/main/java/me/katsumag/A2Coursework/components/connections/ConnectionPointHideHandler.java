package me.katsumag.A2Coursework.components.connections;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import me.katsumag.A2Coursework.components.CircuitComponent;
import me.katsumag.A2Coursework.components.ComponentStore;
import me.katsumag.A2Coursework.util.ParentHelper;
import org.girod.javafx.svgimage.SVGImage;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class ConnectionPointHideHandler implements EventHandler<MouseEvent> {

    /**
     * @param event the event which occurred
     */
    @Override
    public void handle(MouseEvent event) {
        if (! (event.getSource() instanceof SVGImage image)) { return; }

        // image is still on left pane
        if (image.getParent() instanceof VBox) { return; }

        CircuitComponent component = new ComponentStore().getComponentByUUID((UUID) image.getProperties().get("ComponentUUID"));
        component.getConnections().hideConnectionPoints(image);

    }
}
