package me.katsumag.A2Coursework.truth_table.parser;

import me.katsumag.A2Coursework.util.ObjectHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents every logic gate
 */
public class OperatorExpression extends Expression {

    private final List<Expression> expressions;

    public OperatorExpression(OperatorExpressionType type, Expression... expressions) {
        super(type.getName());
        this.expressions = new ArrayList<>(Arrays.asList(expressions));
    }

    @Override
    public String toString() {
        return String.format("%sExpression[first=[%s], second=[%s]]", this.getExpressionType(), getFirstInput(), ObjectHelper.toStringOrNull(getSecondInput()));
    }

    /**
     * @return the first input to the gate. Can never be null as all gates take at least one input
     */
    public @NotNull Expression getFirstInput() {
        return this.expressions.get(0);
    }

    /**
     * @return the second input to the gate. Can be null if the gate is a NOT gate.
     */
    public @Nullable  Expression getSecondInput() {
        if (!Objects.equals(getExpressionType(), "NOT")) {
            return this.expressions.get(1);
        } else {
            return null;
        }
    }

}
