package me.katsumag.A2Coursework.truth_table.parser;

public class ComputedExpression extends Expression {

    private final boolean value;

    public ComputedExpression(boolean computedValue) {
        super("Computed");
        this.value = computedValue;
    }

    @Override
    public String toString() {
        return String.format("ComputedExpression[input=%b]", this.value);
    }

    public boolean getComputedValue() { return this.value; }

}
