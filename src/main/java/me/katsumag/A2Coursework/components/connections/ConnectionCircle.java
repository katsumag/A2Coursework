package me.katsumag.A2Coursework.components.connections;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
  * Exactly the same as a regular circle, only centerX and centerY properties are updated on relocation
  * For some reason, Circle doesn't override the relocate implementation it inherits from {@link javafx.scene.Node}
  * Therefore, circle specific things like centreX/Y are never updated.
 */
public class ConnectionCircle extends Circle {

    public ConnectionCircle(double x, double y, double radius, Color colour) {
        super(x, y, radius, colour);
    }

    @Override
    public void relocate(double x, double y) {
        super.relocate(x, y);
        setCenterX(x);
        setCenterY(y);
    }

}
