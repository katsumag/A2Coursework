package me.katsumag.A2Coursework.truth_table.parser;

public class ORExpression extends Expression {

    private final Expression firstInput, secondInput;

    public ORExpression(Expression firstInput, Expression secondInput) {
        super("OR");
        this.firstInput = firstInput;
        this.secondInput = secondInput;
    }

    @Override
    public String toString() {
        return String.format("ORExpression[first=%s, second=%s]", this.firstInput.toString(), this.secondInput.toString());
    }
    
    public Expression getFirstInput() { return this.firstInput; }
    public Expression getSecondInput() { return this.secondInput; }

}
