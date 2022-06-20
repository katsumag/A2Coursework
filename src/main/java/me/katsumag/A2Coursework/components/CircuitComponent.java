package me.katsumag.A2Coursework.components;

/*
 * This abstract class defines the default behaviour of a circuit component
 */

import me.katsumag.A2Coursework.GateStartDragHandler;
import me.katsumag.A2Coursework.util.SVGHelper;
import org.girod.javafx.svgimage.SVGImage;
import org.girod.javafx.svgimage.SVGLoader;

public abstract class CircuitComponent implements CircuitInterface {

    // Each subclass has its own image, this paired with the constructor allows for
    // A much better system than I previosuly had.
    protected SVGImage image;

    /**
     * @param IMAGE_PATH
     * Loads the image from the given path, assigns listeners, adds properties
     * Generally creates the new component with all the correct options.
     */
    protected CircuitComponent(final String IMAGE_PATH) {
        this.image = loadImageFromPath(IMAGE_PATH);
        initDDListeners();
        addTypeProperty();
        allowClickingAnywhere();
    }

    /**
     * @param path the relative path to the image from the resources folder
     * @return the loaded {@link SVGImage}
     */
    private SVGImage loadImageFromPath(String path) {
        return SVGLoader.load(new SVGHelper().getURLOf(path));
    }

    /**
     * Adds drag  drop listeners
     */
    private void initDDListeners() {
        SVGImage svgImage = this.getImage();
        svgImage.setOnDragDetected(new GateStartDragHandler());
    }

    /**
     * Adds a property to the {@link javafx.scene.Node} which allows me to identify what it's type is so that I can
     * spawn a new component of the same type in the proper position
     */
    private void addTypeProperty() {
        this.getImage().getProperties().put("CircuitComponentType", this.getType().getName());
    }

    /**
     * Set {@link javafx.scene.Node}#setPickOnBounds to true
     * This changes click detection to check if a click is inside
     * The Node's bounds rather than the shape
     * TL:DR; Allows clicking on transparent parts of the images
     */
    private void allowClickingAnywhere() {
        this.getImage().setPickOnBounds(true);
    }

}
