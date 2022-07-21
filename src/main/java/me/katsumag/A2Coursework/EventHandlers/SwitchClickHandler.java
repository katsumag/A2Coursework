package me.katsumag.A2Coursework.EventHandlers;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.components.CircuitComponentType;
import me.katsumag.A2Coursework.components.ComponentStore;
import me.katsumag.A2Coursework.components.Switch;
import org.girod.javafx.svgimage.SVGImage;

import java.util.UUID;

public class SwitchClickHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {

        // getSource and getTarget return the same class here.
        if (! (event.getTarget() instanceof SVGImage image)) return;

        if (image.getProperties().get("CircuitComponentType") != CircuitComponentType.SWITCH.getName()) return;


        // Change to getting by UUID to avoid having to store a reference to the Switch instance on the SVGImage.
        Switch switchInstance = (Switch) new ComponentStore().getComponentByUUID((UUID)image.getProperties().get("ComponentUUID"));

        System.out.println("Switch click listener");
        switchInstance.swapState();
        event.consume();

    }

}
