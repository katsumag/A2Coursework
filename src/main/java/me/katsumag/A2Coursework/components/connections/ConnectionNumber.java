package me.katsumag.A2Coursework.components.connections;

/**
 * Represents the number of inputs and outputs of a component
 */
public enum ConnectionNumber {

    NONE(0),
    ONE(1),
    TWO(2);

    private final Integer number;

    // using a constrcutor rather than ordinal in case there's a theoretical component with 4 inputs, but none with 3
    ConnectionNumber(Integer num) {
        this.number = num;
    }

    public Integer getNumber() {
        return number;
    }
}
