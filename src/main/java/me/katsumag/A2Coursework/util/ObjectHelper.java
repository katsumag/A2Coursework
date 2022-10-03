package me.katsumag.A2Coursework.util;

public class ObjectHelper {

    public static Object toStringOrNull(Object obj) {
        if (obj == null) { return null; }
        else { return obj.toString(); }
    }

}
