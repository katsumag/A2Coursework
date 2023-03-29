package me.katsumag.A2Coursework.util;

import java.net.URL;

public class SVGHelper {

    /*
     * gets the URL from the resourcePath relative to the resources directory
     */
    public URL getURLOf(String resourcePath) {

        return this.getClass().getClassLoader().getResource(resourcePath);

    }

}
