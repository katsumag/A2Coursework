package me.katsumag.A2Coursework.components.connections;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import me.katsumag.A2Coursework.components.ComponentStore;
import org.girod.javafx.svgimage.SVGImage;

import java.util.UUID;

/** Represents an individual connection point on an {@link SVGImage} */
public class Connection {

    private final ConnectionCircle circle;

    private Double x, y;
    private UUID uuid = UUID.randomUUID();
    private Connection connectedPoint;
    private Line connectedLine;
    private SVGImage parentImage;

    public Connection(Double x, Double y, SVGImage image) {

        // Create the circle to represent the connection point
        this.circle = new ConnectionCircle(x, y, 5, Color.rgb(0, 0, 0));

        // Add event handlers
        this.circle.setOnDragDetected(new ConnectionDragDetectedHandler());
        this.circle.setOnMouseDragReleased(new ConnectionDragDroppedHandler());

        // Set picking by bounds rather than opaque shape
        this.circle.setPickOnBounds(true);

        // Register this connection, so I can find it again from its circle.
        new ComponentStore().registerConnection(this);
        this.circle.getProperties().put("ConnectionUUID", this.uuid);

        this.parentImage = image;
    }

    public SVGImage getParentImage() { return parentImage; }

    public Point2D getLocation() {
        return new Point2D(this.circle.getCenterX(), this.circle.getCenterY());
    }

    public UUID getUUID() { return this.uuid; }

    public void setConnectedLine(Line line) {this.connectedLine = line;}

    public Line getConnectedLine() { return this.connectedLine;}

    public void setConnectedPoint(Connection object) { this.connectedPoint =  object;}

    public Connection getConnectedPoint() { return connectedPoint; }

    public ConnectionCircle getCircle() { return this.circle; }
}
