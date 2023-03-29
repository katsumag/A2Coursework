package me.katsumag.A2Coursework.truth_table.parser;

/**
 * Holds a representation of the type of every expression
 */
public enum OperatorExpressionType {

    AND("AND"),
    OR("OR"),
    NOT("NOT");

    private final String type;

    OperatorExpressionType(String name) {
        this.type = name;
    }

    public String getName() { return this.type; }

}
