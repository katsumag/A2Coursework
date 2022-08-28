package me.katsumag.A2Coursework.components.connections;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import me.katsumag.A2Coursework.components.ComponentStore;
import org.girod.javafx.svgimage.SVGImage;

import java.util.UUID;

public class Connection {

    private final Circle circle;

    private Double x, y;
    private UUID uuid = UUID.randomUUID();
    private Connection connectedPoint;
    private Line connectedLine;
    private SVGImage parentImage;

    public Connection(Double x, Double y, SVGImage image) {

        this.x = x;
        this.y = y;
        this.circle = new Circle(x, y, 5, Color.rgb(0, 0, 0));
        this.circle.setOnDragDetected(new ConnectionDragDetectedHandler());
        this.circle.setOnMouseDragReleased(new ConnectionDragHandler());
        //this.circle.setOnDragDropped(new ConnectionDragDroppedHandler());
        this.circle.setPickOnBounds(true);

        // register this connection so I can find it again from it's circle.
        new ComponentStore().registerConnection(this);
        this.circle.getProperties().put("ConnectionUUID", this.uuid);

        this.parentImage = image;
    }

    public SVGImage getParentImage() { return parentImage; }

    public Point2D getLocation() { return new Point2D(this.x, this.y); }

    public UUID getUUID() { return this.uuid; }

    public void setConnectedLine(Line line) {this.connectedLine = line;}

    public Line getConnectedLine() { return this.connectedLine;}

    public void setConnectedPoint(Connection object) { this.connectedPoint =  object;}

    public Connection getConnectedPoint() {
        return connectedPoint;
    }

    public boolean isOccupied() { return connectedPoint != null; }

    public Circle getCircle() {
        return this.circle;
    }
}
