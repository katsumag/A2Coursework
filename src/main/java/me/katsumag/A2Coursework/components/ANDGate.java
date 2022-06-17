package me.katsumag.A2Coursework.components;

import org.girod.javafx.svgimage.SVGImage;

public class ANDGate extends CircuitComponent {

    // constants
    private static final String IMAGE_PATH = "./images/AND gate.svg";
    private static final CircuitComponentType type = CircuitComponentType.AND;

    private final SVGImage image;

    public ANDGate() {
        this.image = loadImageFromPath(IMAGE_PATH);
        initDDListeners();
    }

    @Override
    public SVGImage getImage(){
        return this.image;
    }

    @Override
    public CircuitComponentType getType() {
        return type;
    }

}
