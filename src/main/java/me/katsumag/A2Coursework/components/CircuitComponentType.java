package me.katsumag.A2Coursework.components;

/**
  *  Represents the type of each circuit component
 */
public enum CircuitComponentType {

    // Changing this to fix an issue at GateStopDragHandler#28 where valueOf throws an excpetion  because these were lowercase.

    AND("AND"),
    OR("OR"),
    NOT("NOT"),
    SWITCH("SWITCH"),
    LAMP("LAMP");

    private final String name;

    CircuitComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
