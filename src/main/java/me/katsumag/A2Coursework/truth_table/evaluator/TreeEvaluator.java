package me.katsumag.A2Coursework.truth_table.evaluator;

import me.katsumag.A2Coursework.truth_table.parser.ComputedExpression;
import me.katsumag.A2Coursework.truth_table.parser.Expression;
import me.katsumag.A2Coursework.truth_table.parser.OperatorExpression;

import java.util.Objects;

public class TreeEvaluator {

    public boolean evaluate(Expression expression) throws ClassNotFoundException {

        // if XExpression -> check inputs not ComputedExpression, calc
        // if ComputedExpression, recurse and then use return value in ComputedExpression

        // to avoid having to do all of this, have AND, OR, NOT extend OperatorExpression, and put default behavior in there
        if (expression instanceof OperatorExpression operatorExpression) {

            ComputedExpression first, second;

            // compute first input
            if (operatorExpression.getFirstInput() instanceof ComputedExpression computedExpression) {
                first = computedExpression;
            } else {
                first = new ComputedExpression(evaluate(operatorExpression.getFirstInput()));
            }

            // compute second input

            if (Objects.equals(expression.getExpressionType(), "NOT")) {
                // dont compute second value.
                // null instanceof x returns false, so it would try to recurse
                second = null;
            } else {
                if (operatorExpression.getSecondInput() instanceof ComputedExpression secondComputedExpression) {
                    second = secondComputedExpression;
                } else {
                    second = new ComputedExpression(evaluate(operatorExpression.getSecondInput()));
                }
            }

            switch (operatorExpression.getExpressionType()) {
                case "AND" -> { return first.getComputedValue() && second.getComputedValue(); }
                case "OR" -> { return first.getComputedValue() || second.getComputedValue(); }
                case "NOT" -> { return !first.getComputedValue(); }
            }

        }

        return ((ComputedExpression) expression).getComputedValue();
    }

}