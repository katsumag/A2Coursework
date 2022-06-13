package me.katsumag.A2Coursework;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class SVGLoader {

    public Image load(String resourcePath) throws FileNotFoundException {

        InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream(resourcePath);

        if (inputStream == null) {
            throw new FileNotFoundException("File " + resourcePath + " not found in resources folder. Check the provided path and ensure the file exists.");
        }

        return new Image(inputStream);

    }

}
