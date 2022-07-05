package me.katsumag.A2Coursework.components;

import me.katsumag.A2Coursework.components.connections.ConnectionManager;
import me.katsumag.A2Coursework.components.connections.ConnectionNumber;
import me.katsumag.A2Coursework.components.connections.ConnectionPointHideHandler;
import me.katsumag.A2Coursework.components.connections.ConnectionPointShowHandler;
import org.girod.javafx.svgimage.SVGImage;

public class ANDGate extends CircuitComponent {

    // constants
    private static final String IMAGE_PATH = "./images/AND gate.svg";
    private static final CircuitComponentType type = CircuitComponentType.AND;

    private final ConnectionManager connectionManager = new ConnectionManager(ConnectionNumber.TWO, ConnectionNumber.ONE);

    public ANDGate() {
        super(IMAGE_PATH);
        this.image.setOnMouseEntered(new ConnectionPointShowHandler());
        this.image.setOnMouseExited(new ConnectionPointHideHandler());
    }

    @Override
    public SVGImage getImage() {
        return this.image;
    }

    @Override
    public CircuitComponentType getType() {
        return type;
    }

    @Override
    public ConnectionManager getConnections() {
        return this.connectionManager;
    }

}
