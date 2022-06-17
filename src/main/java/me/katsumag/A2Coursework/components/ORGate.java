package me.katsumag.A2Coursework.components;

import org.girod.javafx.svgimage.SVGImage;

public class ORGate extends CircuitComponent {

    // constants
    private static final String IMAGE_PATH = "./images/OR gate.svg";
    private static final CircuitComponentType type = CircuitComponentType.OR;

    private final SVGImage image;

    public ORGate() {
        this.image = loadImageFromPath(IMAGE_PATH);
        initDDListeners();
        addTypeProperty();
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
