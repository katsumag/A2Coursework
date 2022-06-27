package me.katsumag.A2Coursework.EventHandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.components.CircuitComponentType;
import me.katsumag.A2Coursework.components.Switch;
import org.girod.javafx.svgimage.SVGImage;

public class SwitchClickHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {

        // This is printed, so one of the following checks must fail, but how?
        System.out.println("Switch Clicked");

        // The result of this when clicking on the blue square is a JavaFX Rectangle.
        System.out.println(event.getTarget().getClass());

        // getSource and getTarget return the same class here.
        if (! (event.getTarget() instanceof SVGImage image)) return;

        if (image.getProperties().get("CircuitComponentType") != CircuitComponentType.SWITCH.getName()) return;

        /*
         * Possibly not best practice, maybe replace with changing the value of a BooleanProperty and having
         * the Switch register a ChangeListener for it instead? Would still have to add it as a property
         * of the SVGImage but at least it wouldn't be the whole Switch class. I'm not sure.
         */

        Switch switchInstance = (Switch) image.getProperties().get("SwitchInstance");

        switchInstance.swapState();

    }

}
