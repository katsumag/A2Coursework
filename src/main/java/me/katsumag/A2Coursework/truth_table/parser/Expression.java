package me.katsumag.A2Coursework.truth_table.parser;

import java.io.Serializable;

public class Expression implements Serializable {

    private final String expressionType;

    public Expression(String type) {
        this.expressionType = type;
    }

    public String getExpressionType() { return this.expressionType; }

}
