package me.katsumag.A2Coursework;

import org.girod.javafx.svgimage.SVGImage;
import org.girod.javafx.svgimage.SVGLoader;

public enum LogicComponents {

    AND_GATE(SVGLoader.load(new SVGHelper().getURLOf("./images/AND gate.svg"))),
    OR_GATE(SVGLoader.load(new SVGHelper().getURLOf("./images/OR gate.svg"))),
    NOT_GATE(SVGLoader.load(new SVGHelper().getURLOf("./images/NOT gate.svg")));

    private final SVGImage image;

    private LogicComponents(final SVGImage image) {
        this.image = image;
    }

}
