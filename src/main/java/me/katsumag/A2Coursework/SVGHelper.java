package me.katsumag.A2Coursework;

import java.io.FileNotFoundException;
import java.net.URL;

public class SVGHelper {

    public URL getURLOf(String resourcePath) throws FileNotFoundException {

        URL url =  this.getClass().getClassLoader().getResource(resourcePath);

        if (url == null) {
            throw new FileNotFoundException("File " + resourcePath + " not found in resources folder. Check the provided path and ensure the file exists.");
        }

        return url;

    }

}
