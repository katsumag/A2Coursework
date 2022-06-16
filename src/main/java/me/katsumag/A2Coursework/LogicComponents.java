package me.katsumag.A2Coursework;

import org.girod.javafx.svgimage.SVGImage;
import org.girod.javafx.svgimage.SVGLoader;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;

public enum LogicComponents {

    AND_GATE(LogicComponentType.AND, SVGLoader.load(new SVGHelper().getURLOf("./images/AND gate.svg"))),
    OR_GATE(LogicComponentType.OR, SVGLoader.load(new SVGHelper().getURLOf("./images/OR gate.svg"))),
    NOT_GATE(LogicComponentType.NOT, SVGLoader.load(new SVGHelper().getURLOf("./images/NOT gate.svg")));

    private final LogicComponentType type;
    private final SVGImage image;

    LogicComponents(final @NotNull LogicComponentType logicComponentType, final @NotNull SVGImage svgImage) {
        this.type = logicComponentType;
        this.image = svgImage;
    }

}
