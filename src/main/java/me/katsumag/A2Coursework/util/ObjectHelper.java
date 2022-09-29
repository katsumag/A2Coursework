package me.katsumag.A2Coursework.util;

import me.katsumag.A2Coursework.truth_table.parser.Expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectHelper {

    public static Object toStringOrNull(Object obj) {
        if (obj == null) { return null; }
        else { return obj.toString(); }
    }

    public List<Expression> expressionArrayToList(Expression[] expressions) {
        return new ArrayList<>(Arrays.asList(expressions));
    }

}
