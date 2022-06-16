package me.katsumag.A2Coursework;

public enum LogicComponentType {

    AND("and"),
    OR("or"),
    NOT("not");

    private final String name;

    LogicComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
