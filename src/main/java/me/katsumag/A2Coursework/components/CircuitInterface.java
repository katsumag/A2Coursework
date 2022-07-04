package me.katsumag.A2Coursework.components;

import me.katsumag.A2Coursework.components.connections.ConnectionManager;
import org.girod.javafx.svgimage.SVGImage;

import java.util.UUID;

/**
 * This interface defines every standard method of a {@link CircuitComponent}
 */
public interface CircuitInterface {

    /**
     * @return the circuit component's {@link SVGImage}
     */
    SVGImage getImage();

    /**
     * @return the circuit component's {@link CircuitComponentType}
     */
    CircuitComponentType getType();

    /**
     * @return the circuit component's {@link ConnectionManager}
     */
    ConnectionManager getConnections();

    /**
     * @return the circuit component's {@link UUID}
     */
    UUID getUUID();

}
