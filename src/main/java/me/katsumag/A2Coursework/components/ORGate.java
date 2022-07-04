package me.katsumag.A2Coursework.components;

import me.katsumag.A2Coursework.components.connections.ConnectionManager;
import me.katsumag.A2Coursework.components.connections.ConnectionNumber;
import org.girod.javafx.svgimage.SVGImage;

public class ORGate extends CircuitComponent {

    // constants
    private static final String IMAGE_PATH = "./images/OR gate.svg";
    private static final CircuitComponentType type = CircuitComponentType.OR;

    private final ConnectionManager connectionManager = new ConnectionManager(ConnectionNumber.TWO, ConnectionNumber.ONE);

    public ORGate() {
        super(IMAGE_PATH);
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
