package me.katsumag.A2Coursework.components;

import org.girod.javafx.svgimage.SVGImage;

public class ANDGate extends CircuitComponent {

    // constants
    private static final String IMAGE_PATH = "./images/AND gate.svg";
    private static final CircuitComponentType type = CircuitComponentType.AND;

    public ANDGate() {
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

}
