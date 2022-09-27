package me.katsumag.A2Coursework.truth_table.parser;

public class NOTExpression extends Expression {

    private final Expression input;

    public NOTExpression(Expression input) {
        super("NOT");
        this.input = input;
    }

    @Override
    public String toString() {
        return String.format("NOTExpression[input=%s]", this.input.toString());
    }

    public Expression getInput() { return this.input; }
}
