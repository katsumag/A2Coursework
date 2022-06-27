package me.katsumag.A2Coursework.components;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import me.katsumag.A2Coursework.EventHandlers.GateStartDragHandler;
import me.katsumag.A2Coursework.EventHandlers.SwitchClickHandler;
import me.katsumag.A2Coursework.util.ParentHelper;
import org.girod.javafx.svgimage.SVGImage;

import java.lang.reflect.InvocationTargetException;

public class Switch extends CircuitComponent {

    // Usual constants
    private static final String BASE_IMAGE_PATH = "./images/Switch_%s.svg";
    private static final CircuitComponentType type = CircuitComponentType.SWITCH;

    // Image to show when switch is on. `image` will be the default + off image
    private final SVGImage onImage = this.loadImageFromPath(BASE_IMAGE_PATH.formatted("ON"));

    // Keep track of the state of the switch
    private boolean state = false;

    public Switch() {
        super(BASE_IMAGE_PATH.formatted("OFF"));
        addDragHandler();
        addComponentType();
        setClickHandler();
        addInstanceReference();
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
     * Adds a reference to this Switch instance as a property of both SVGImages
     * This is done so that {@link SwitchClickHandler} can access the state of the switch to swap it
     */
    private void addInstanceReference() {
        this.onImage.getProperties().put("SwitchInstance", this);
        this.image.getProperties().put("SwitchInstance", this);
    }

    /**
     * This is done for most components through the constructor of {@link CircuitComponent}
     * However, since this uses two images, I need to manually add the property to the second image
     */
    private void addComponentType() {
        this.onImage.getProperties().put("CircuitComponentType", type.getName());
    }

    /**
     * This is usually done through the constructor of {@link CircuitComponent}
     * However, since this uses two images, I need to manually set the drag handler on the second image
     */
    private void addDragHandler() {
        // image already has it applied through the constructor
        this.onImage.setOnDragDetected(new GateStartDragHandler());
    }

    /**
     * Sets the mouse click event handler for both images
     * Also enables setPickOnBounds for the second image.
     */
    private void setClickHandler() {
        // register click handler for both images
        this.image.setOnMouseClicked(new SwitchClickHandler());
        this.onImage.setPickOnBounds(true);
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

        try {

            if (this.state) {
                ObservableList<Node> children = parentHelper.getChildrenOf(this.image.getParent());
                children.remove(this.image);
                this.onImage.relocate(this.image.getLayoutX(), this.image.getLayoutY());
                children.add(this.onImage);
            } else {
                ObservableList<Node> children = parentHelper.getChildrenOf(this.onImage.getParent());
                children.remove(this.onImage);
                this.onImage.relocate(this.image.getLayoutX(), this.image.getLayoutY());
                children.add(this.image);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            ex.printStackTrace();
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
}
