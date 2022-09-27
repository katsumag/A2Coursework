package me.katsumag.A2Coursework.truth_table.parser;

public class ANDExpression  extends Expression {

    private final Expression firstInput, secondInput;

    public ANDExpression(Expression firstInput, Expression secondInput) {
        super("AND");
        this.firstInput = firstInput;
        this.secondInput = secondInput;
    }

    @Override
    public String toString() {
        return String.format("ANDExpression[first=%s, second=%s]", this.firstInput.toString(), this.secondInput.toString());
    }

    public Expression getFirstInput() { return this.firstInput; }
    public Expression getSecondInput() { return this.secondInput; }



}
