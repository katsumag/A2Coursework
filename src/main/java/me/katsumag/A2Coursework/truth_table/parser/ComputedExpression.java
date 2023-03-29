package me.katsumag.A2Coursework.truth_table.parser;

/**
 * Represents all switches. All this does is hold a value, but extend Expression to make my life so much easier
 */
public class ComputedExpression extends Expression {

    private boolean value;

    public ComputedExpression(boolean computedValue) {
        super("Computed");
        this.value = computedValue;
    }

    @Override
    public String toString() {
        return String.format("ComputedExpression[input=%b]", this.value);
    }

    public boolean getComputedValue() { return this.value; }

    public void setComputedValue(boolean computedValue) { this.value = computedValue; }

}
