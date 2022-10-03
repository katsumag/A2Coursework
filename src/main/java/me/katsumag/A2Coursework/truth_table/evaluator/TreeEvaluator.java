package me.katsumag.A2Coursework.truth_table.evaluator;

import me.katsumag.A2Coursework.truth_table.parser.ComputedExpression;
import me.katsumag.A2Coursework.truth_table.parser.Expression;
import me.katsumag.A2Coursework.truth_table.parser.OperatorExpression;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TreeEvaluator {

    public boolean evaluate(Expression expression) {

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

    public boolean evaluateWith(Expression expression, List<Boolean> bits) {
        // modify expression contents by either serialising or whatever
        // then, use existing method

        return evaluate(replaceComputedExpression(expression, bits));
    }

    private Expression replaceComputedExpression(Expression expression, List<Boolean> bits) {

        if (expression instanceof OperatorExpression operatorExpression) {
            // do nothing, check left and right children
            replaceComputedExpression(operatorExpression.getFirstInput(), bits);
            if (operatorExpression.getSecondInput() != null) { replaceComputedExpression(operatorExpression.getSecondInput(), bits); }
        }

        if (expression instanceof ComputedExpression) {
            ((ComputedExpression) expression).setComputedValue(bits.get(0));
            // I love pass by reference
            bits = leftShiftBits(bits);
        }

        return expression;
    }

    private List<Boolean> leftShiftBits(List<Boolean> bits) {
        for (int i = 1; i < bits.size(); i++) {
            bits.set(i-1, bits.get(i));
        }
        bits.remove(bits.size()-1);
        return bits;
    }

}