package me.katsumag.A2Coursework.components;

import javafx.scene.Parent;
import me.katsumag.A2Coursework.components.connections.ConnectionManager;
import me.katsumag.A2Coursework.components.connections.ConnectionNumber;
import me.katsumag.A2Coursework.util.ParentHelper;
import org.girod.javafx.svgimage.SVGImage;

public class Lamp extends CircuitComponent {

    private static final String BASE_IMAGE_PATH = "./images/Lamp_%s.svg";
    private static final CircuitComponentType componentType = CircuitComponentType.LAMP;

    private final SVGImage onImage = loadAndProcessImageFromPath(BASE_IMAGE_PATH.formatted("ON"));
    private final ConnectionManager connectionManager = new ConnectionManager(ConnectionNumber.ONE, ConnectionNumber.NONE);

    private boolean state = false;

    public Lamp() {
        super(BASE_IMAGE_PATH.formatted("OFF"));
        stopChildrenInteracting();
    }

    /**
     * Swap the state of the lamp.
     */
    public void swapState() {
        this.state = !this.state;

        ParentHelper parentHelper = new ParentHelper();

        if (this.state) {
            // Going from off -> on
            Parent pane = this.image.getParent();
            // hide off image
            parentHelper.removeChildFrom(pane, this.image);
            // relocate on image to be over it
            this.onImage.relocate(this.image.getLayoutX(), this.image.getLayoutY());
            // show on image
            parentHelper.addChildTo(pane, this.onImage);
        } else {
            // Going from on -> off
            Parent pane = this.onImage.getParent();
            // hide on image
            parentHelper.removeChildFrom(pane, this.onImage);
            // relocate off image to be over it
            this.image.relocate(this.onImage.getLayoutX(), this.onImage.getLayoutY());
            // show off image
            parentHelper.addChildTo(pane, this.image);
        }

    }

    /**
     * Sets the {@link SVGImage}'s children to be transparent to mouse events.
     * Although they are it's children, if they're solid objects they appear on top of the image
     * And so capture click events for the blue bit of the on button and the black rectangle of the off button
     * Doing this mitigates the issue
     */
    private void stopChildrenInteracting() {
        this.onImage.getChildren().forEach(it -> it.setMouseTransparent(true));
        this.image.getChildren().forEach(it -> it.setMouseTransparent(true));
    }

    @Override
    public SVGImage getImage() {
        if (this.state) {
            return this.onImage;
        } else {
            return this.image;
        }
    }

    @Override
    public CircuitComponentType getType() {
        return componentType;
    }

    @Override
    public ConnectionManager getConnections() {
        return this.connectionManager;
    }
}
