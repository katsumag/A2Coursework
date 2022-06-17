package me.katsumag.A2Coursework.util;

import javafx.scene.Node;

public class DragUtil {

    public static boolean isCenterPanel(Object object) {

        if (! (object instanceof Node)) return false;

        Node node = (Node) object;

        // check to see if the drag target is the center panel
        if (! node.getProperties().containsKey("CenterPanel")) return false;

        // probably unnecessary checks, but doesn't hurt anyway. Ensures CenterPanel is true
        if (! (node.getProperties().get("CenterPanel") instanceof Boolean)) return false;
        if (! ((Boolean)(node.getProperties().get("CenterPanel")))) return false;

        return true;

    }

}
