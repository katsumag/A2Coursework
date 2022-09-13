package me.katsumag.A2Coursework.components.connections;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ConnectionCircle extends Circle {

    public ConnectionCircle(double x, double y, double radius, Color colour) {
        super(x, y, radius, colour);
    }

    @Override
    public void relocate(double x, double y) {
        setCenterX(x);
        setCenterY(y);
    }

}
