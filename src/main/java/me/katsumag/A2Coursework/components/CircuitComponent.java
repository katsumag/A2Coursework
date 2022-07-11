package me.katsumag.A2Coursework.components;

/*
 * This abstract class defines the default behaviour of a circuit component
 */

import me.katsumag.A2Coursework.EventHandlers.GateStartDragHandler;
import me.katsumag.A2Coursework.components.connections.ConnectionPointHideHandler;
import me.katsumag.A2Coursework.components.connections.ConnectionPointShowHandler;
import me.katsumag.A2Coursework.util.SVGHelper;
import org.girod.javafx.svgimage.SVGImage;
import org.girod.javafx.svgimage.SVGLoader;

import java.util.UUID;

public abstract class CircuitComponent implements CircuitInterface {

    // Each subclass has its own image, this paired with the constructor allows for
    // A much better system than I previously had.
    protected SVGImage image;

    protected final UUID uuid = UUID.randomUUID();

    /**
     * @param IMAGE_PATH
     * Loads the image from the given path, assigns listeners, adds properties
     * Generally creates the new component with all the correct options.
     */
    protected CircuitComponent(final String IMAGE_PATH) {
        this.image = loadAndProcessImageFromPath(IMAGE_PATH);
        new ComponentStore().registerComponent(this);
    }

    protected SVGImage loadAndProcessImageFromPath(String path) {
        SVGImage image = loadImageFromPath(path);
        initDDListeners(image);
        addTypeProperty(image);
        allowClickingAnywhere(image);
        registerComponent(image);
        addConnectionListener(image);
        return image;
    }

    private void addConnectionListener(SVGImage image) {
        image.setOnMouseEntered(new ConnectionPointShowHandler());
        image.setOnMouseExited(new ConnectionPointHideHandler());
    }

    private void registerComponent(SVGImage image) {
        image.getProperties().put("ComponentUUID", this.getUUID());
    }

    /**
     * @param path the relative path to the image from the resources folder
     * @return the loaded {@link SVGImage}
     * Protected for use in multi-image components like {@link Switch}
     */
    private SVGImage loadImageFromPath(String path) {
        return SVGLoader.load(new SVGHelper().getURLOf(path));
    }

    /**
     * Adds drag  drop listeners
     */
    private void initDDListeners(SVGImage svgImage) {
        svgImage.setOnDragDetected(new GateStartDragHandler());
    }

    /**
     * Adds a property to the {@link javafx.scene.Node} which allows me to identify what it's type is so that I can
     * spawn a new component of the same type in the proper position
     */
    private void addTypeProperty(SVGImage svgImage) {
        svgImage.getProperties().put("CircuitComponentType", this.getType().getName());
    }

    /**
     * Set {@link javafx.scene.Node}#setPickOnBounds to true
     * This changes click detection to check if a click is inside
     * The Node's bounds rather than the shape
     * TL:DR; Allows clicking on transparent parts of the images
     */
    private void allowClickingAnywhere(SVGImage svgImage) {
        svgImage.setPickOnBounds(true);
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

}
