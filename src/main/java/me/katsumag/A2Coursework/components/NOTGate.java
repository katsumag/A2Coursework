package me.katsumag.A2Coursework.components;

import org.girod.javafx.svgimage.SVGImage;

public class NOTGate extends CircuitComponent {

    // constants
    private static final String IMAGE_PATH = "./images/NOT gate.svg";
    private static final CircuitComponentType type = CircuitComponentType.NOT;

    private final SVGImage image;

    public NOTGate() {
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
