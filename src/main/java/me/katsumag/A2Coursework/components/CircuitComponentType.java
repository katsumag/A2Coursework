package me.katsumag.A2Coursework.components;

public enum CircuitComponentType {

    AND("and"),
    OR("or"),
    NOT("not");

    private final String name;

    CircuitComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
