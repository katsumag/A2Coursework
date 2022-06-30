package me.katsumag.A2Coursework.components;

import org.girod.javafx.svgimage.SVGImage;

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

}
