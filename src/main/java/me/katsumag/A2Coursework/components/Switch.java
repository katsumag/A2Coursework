package me.katsumag.A2Coursework.components;

import org.girod.javafx.svgimage.SVGImage;

public class Switch extends CircuitComponent {

    private static final String BASE_IMAGE_PATH = "./images/Switch_%s.svg";
    private static final CircuitComponentType type = CircuitComponentType.SWITCH;

    // Image to show when switch is on. `image` will be the default + off image
    private final SVGImage onImage = this.loadImageFromPath(BASE_IMAGE_PATH.formatted("ON"));

    private boolean state = false;

    public Switch() {
        super(BASE_IMAGE_PATH.formatted("OFF"));
    }

    @Override
    public SVGImage getImage() {
        if (this.state) {
            return this.onImage;
        } else {
            return this.image;
        }
    }

    @Override
    public CircuitComponentType getType() {
        return type;
    }
}
