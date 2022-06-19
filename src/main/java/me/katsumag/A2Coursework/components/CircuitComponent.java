package me.katsumag.A2Coursework.components;

/*
 * This abstract class defines the default behaviour of a circuit component
 */

import me.katsumag.A2Coursework.GateDragMovementHandler;
import me.katsumag.A2Coursework.GateStartDragHandler;
import me.katsumag.A2Coursework.GateStopDragHandler;
import me.katsumag.A2Coursework.util.SVGHelper;
import org.girod.javafx.svgimage.SVGImage;
import org.girod.javafx.svgimage.SVGLoader;

public abstract class CircuitComponent implements CircuitInterface {

    /*
     * Because each subclass will have it's own image, I can't implement getImage
     * It's protected so that any subclasses can access it, but it's not public
     * A side effect is that anything in the same package can access it from subclasses.
     */

    /**
     *
     * @param path the relative path to the image from the resources folder
     * @return the loaded {@link SVGImage}
     */
    protected SVGImage loadImageFromPath(String path) {
        return SVGLoader.load(new SVGHelper().getURLOf(path));
    }

    /**
     * Adds drag  drop listeners
     */
    protected void initDDListeners() {
        SVGImage svgImage = this.getImage();
        svgImage.setOnDragDetected(new GateStartDragHandler());
        svgImage.setOnDragDropped(new GateStopDragHandler());
    }

    /**
     * Adds a property to the {@link javafx.scene.Node} which allows me to identify what it's type is so that I can
     * spawn a new component of the same type in the proper position
     */
    protected void addTypeProperty() {
        this.getImage().getProperties().put("CircuitComponentType", this.getType().getName());
    }

}
