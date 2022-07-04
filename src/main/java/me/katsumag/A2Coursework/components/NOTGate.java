package me.katsumag.A2Coursework.components;

import me.katsumag.A2Coursework.components.connections.ConnectionManager;
import me.katsumag.A2Coursework.components.connections.ConnectionNumber;
import org.girod.javafx.svgimage.SVGImage;

public class NOTGate extends CircuitComponent {

    // constants
    private static final String IMAGE_PATH = "./images/NOT gate.svg";
    private static final CircuitComponentType type = CircuitComponentType.NOT;

    private final ConnectionManager connectionManager = new ConnectionManager(ConnectionNumber.ONE, ConnectionNumber.ONE);

    public NOTGate() {
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
