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

    public Point2D getTopLeft() {
        return new Point2D(
                this.image.getLayoutX() - this.imageBounds.getMinX(),
                this.image.getLayoutY() - this.imageBounds.getMinY() + (this.image.getHeight() / 2)
        );
    }

    public Point2D getBottomLeft() {
        return new Point2D(
                this.image.getLayoutX() - this.imageBounds.getMinX(),
                this.image.getLayoutY() + this.imageBounds.getMaxY()
        );
    }

    public Point2D getMiddleLeft() {
        return new Point2D(
                this.image.getLayoutX() - this.imageBounds.getMinX(),
                this.image.getLayoutY() + (0.5 * this.imageBounds.getHeight())
        );
    }

    public Point2D getTopRight() {
        return new Point2D(
                this.image.getLayoutX() + this.imageBounds.getWidth(),
                this.image.getLayoutY() - this.imageBounds.getMinY()
        );
    }

    public Point2D getBottomRight() {
        return new Point2D(
                this.image.getLayoutX() + this.imageBounds.getWidth(),
                this.image.getLayoutY() + this.imageBounds.getMaxY()
        );
    }

    public Point2D getMiddleRight() {
        return new Point2D(
                this.image.getLayoutX() + this.imageBounds.getWidth(),
                this.image.getLayoutY() + (0.5 * this.imageBounds.getHeight())
        );
    }

}
