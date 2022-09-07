package me.katsumag.A2Coursework.components;

import javafx.scene.Parent;
import me.katsumag.A2Coursework.EventHandlers.SwitchClickHandler;
import me.katsumag.A2Coursework.components.connections.ConnectionManager;
import me.katsumag.A2Coursework.components.connections.ConnectionNumber;
import me.katsumag.A2Coursework.util.ParentHelper;
import org.girod.javafx.svgimage.SVGImage;

public class Switch extends CircuitComponent {

    // Usual constants
    private static final String BASE_IMAGE_PATH = "./images/Switch_%s.svg";
    private static final CircuitComponentType type = CircuitComponentType.SWITCH;

    // Image to show when switch is on. `image` will be the default + off image
    private final SVGImage onImage = this.loadAndProcessImageFromPath(BASE_IMAGE_PATH.formatted("ON"));

    // Keep track of the state of the switch
    private boolean state = false;

    private final ConnectionManager connectionManager = new ConnectionManager(ConnectionNumber.NONE, ConnectionNumber.ONE);

    public Switch() {
        super(BASE_IMAGE_PATH.formatted("OFF"));
        setClickHandler();
        stopChildrenInteracting();
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

    /**
     * Sets the mouse click event handler for both images
     * Also enables setPickOnBounds for the second image.
     */
    private void setClickHandler() {
        // register click handler for both images
        this.image.setOnMouseClicked(new SwitchClickHandler());
        this.onImage.setOnMouseClicked(new SwitchClickHandler());
    }

    /**
     * Swap the state of the switch.
     * Because this is an {@link javafx.beans.value.ObservableBooleanValue} its listener will be executed
     * This will perform all necessary changes to the center {@link javafx.scene.layout.Pane}
     */
    public void swapState() {
        this.state = !this.state;

        ParentHelper parentHelper = new ParentHelper();
        ComponentStore componentStore = new ComponentStore();

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
        return type;
    }

    @Override
    public ConnectionManager getConnections() {
       return this.connectionManager;
    }
}
