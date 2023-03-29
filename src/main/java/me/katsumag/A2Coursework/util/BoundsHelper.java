package me.katsumag.A2Coursework.util;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import org.girod.javafx.svgimage.SVGImage;

public class BoundsHelper {

    private final SVGImage image;
    private final Bounds imageBounds;

    public BoundsHelper(SVGImage svgImage) {
        this.image = svgImage;
        this.imageBounds = svgImage.getLayoutBounds();
    }

    // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#layoutXProperty

    /**
     * Used by
     * {@link me.katsumag.A2Coursework.components.ANDGate}
     * {@link me.katsumag.A2Coursework.components.ORGate}
     * Gets the top left co-ordinate of the SVGImage
     */
    public Point2D getTopLeft() {
        return new Point2D(
                this.image.getLayoutX() + this.imageBounds.getMinX(),
                this.image.getLayoutY() - this.imageBounds.getMinY() + (this.image.getHeight() / 2)
        );
    }

    /**
     * Used by
     * {@link me.katsumag.A2Coursework.components.ANDGate}
     * {@link me.katsumag.A2Coursework.components.ORGate}
     * Gets the bottom left co-ordinate of the SVGImage
     */
    public Point2D getBottomLeft() {
        return new Point2D(
                this.image.getLayoutX() + this.imageBounds.getMinX(),
                this.image.getLayoutY() + this.imageBounds.getMaxY() - (this.image.getHeight() / 4)
        );
    }

    /**
     * Used by:
     * {@link me.katsumag.A2Coursework.components.NOTGate}
     * Gets the middle left co-ordinate of the SVGImage
     */
    public Point2D getMiddleLeft() {
        return new Point2D(
                this.image.getLayoutX() + this.imageBounds.getMinX(),
                this.image.getLayoutY() + (0.55 * this.imageBounds.getHeight())
        );
    }

    /**
     * Used by:
     * {@link me.katsumag.A2Coursework.components.Lamp}
     * Gets the bottom middle co-ordinate of the SVGImage
     */
    public Point2D getBottomMiddle() {
        return new Point2D(
                this.image.getLayoutX() + (this.imageBounds.getWidth() / 2),
                this.image.getLayoutY() + this.imageBounds.getMaxY()
        );
    }

    /**
     * Provides the output connection point for the two larger gates
     * Used by:
     * {@link me.katsumag.A2Coursework.components.ANDGate}
     * {@link me.katsumag.A2Coursework.components.ORGate}
     * Gets the middle right co-ordinate of the SVGImage
     */
    public Point2D getMiddleRight() {
        return new Point2D(
                this.image.getLayoutX() + this.imageBounds.getWidth(),
                this.image.getLayoutY() + (0.6 * this.imageBounds.getHeight())
        );
    }

    /**
     * provides the output connection point for the NOT gate and Switch.
     * Used by:
     * {@link me.katsumag.A2Coursework.components.NOTGate}
     * {@link me.katsumag.A2Coursework.components.Switch}
     * Gets the slightly higher middle right co-ordinate of the SVGImage
     */
    public Point2D getHigherMiddleRight() {
        return new Point2D(
                this.image.getLayoutX() + this.imageBounds.getWidth(),
                this.image.getLayoutY() + (0.55 * this.imageBounds.getHeight())
        );
    }

}
