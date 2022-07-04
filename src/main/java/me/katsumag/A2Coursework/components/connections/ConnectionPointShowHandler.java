package me.katsumag.A2Coursework.components.connections;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.girod.javafx.svgimage.SVGImage;

public class ConnectionPointShowHandler implements EventHandler<MouseEvent> {

    /**
     * @param event the event which occurred
     */
    @Override
    public void handle(MouseEvent event) {

        if (! (event.getSource() instanceof SVGImage image)) { return; }

        // need to get the CircuitComponent instance, but instead of adding
        // a terrible workaround, re-work code to have a deployed circuit manager
        // give each component its own UUID add that as a property, then getByUUID
        // possibly generics? idk how that'd really work though since I couldn't use type parameters.

    }
}
