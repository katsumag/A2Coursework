package me.katsumag.A2Coursework.components.connections;

import javafx.event.Event;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.girod.javafx.svgimage.SVGImage;

import java.awt.event.MouseEvent;

public class Connection {

    private final Circle circle;

    private Double x, y;
    private SVGImage connectedObject;

    public Connection(Double x, Double y) {
        this.x = x;
        this.y = y;
        this.circle = new Circle(x, y, 5, Color.rgb(0, 0, 0));
        this.circle.setOnMouseClicked(new ConnectionClickHandler());
        //this.circle.setOnDragOver(new ConnectionDragHandler());
        //this.circle.setOnDragDropped(new ConnectionDragDroppedHandler());
        this.circle.setPickOnBounds(true);
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
