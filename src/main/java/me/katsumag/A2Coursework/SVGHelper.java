package me.katsumag.A2Coursework;

import java.net.URL;

public class SVGHelper {

    public URL getURLOf(String resourcePath) {

        return this.getClass().getClassLoader().getResource(resourcePath);

    }

}
