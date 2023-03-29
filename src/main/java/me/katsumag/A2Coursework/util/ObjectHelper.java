package me.katsumag.A2Coursework.util;

public class ObjectHelper {

    // basically a nullable representation of the input object
    public static Object toStringOrNull(Object obj) {
        if (obj == null) { return null; }
        else { return obj.toString(); }
    }

}
