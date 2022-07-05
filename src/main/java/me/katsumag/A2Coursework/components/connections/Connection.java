package me.katsumag.A2Coursework.components.connections;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.girod.javafx.svgimage.SVGImage;

public class Connection {

    private final Circle circle;

    private Double x, y;
    private SVGImage connectedObject;

    public Connection(Double x, Double y) {
        this.x = x;
        this.y = y;
        this.circle = new Circle(x, y, 2.5, Color.rgb(0, 0, 0));
    }

    public void setConnectedObject(SVGImage object) { this.connectedObject =  object;}

    public SVGImage getConnectedObject() {
        return connectedObject;
    }

    public boolean isOccupied() { return connectedObject != null; }

    public Circle getCircle() {
        return this.circle;
    }
}
