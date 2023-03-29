package me.katsumag.A2Coursework.truth_table.evaluator;

import me.katsumag.A2Coursework.truth_table.parser.ComputedExpression;
import me.katsumag.A2Coursework.truth_table.parser.Expression;
import me.katsumag.A2Coursework.truth_table.parser.OperatorExpression;

import java.util.List;
import java.util.Objects;


/**
 * Handles calculating the output of a circuit
 */
public class TreeEvaluator {

    /**
     * @param expression The parsed expression which represents the current circuit to evaluate
     * @return The state of the final output of the circuit that is attached to the lamp
     */
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

            // account for NOT gates only having one input
            if (Objects.equals(expression.getExpressionType(), "NOT")) {
                // dont compute second value.
                // null instanceof x returns false, so it would try to recurse
                second = null;
            } else {
                if (operatorExpression.getSecondInput() instanceof ComputedExpression secondComputedExpression) {
                    second = secondComputedExpression;
                } else {
                    // recurse to calculate state of expression
                    second = new ComputedExpression(evaluate(operatorExpression.getSecondInput()));
                }
            }

            // At this point I'm only dealing with basic expressions thanks to recursion
            switch (operatorExpression.getExpressionType()) {
                case "AND" -> { return first.getComputedValue() && second.getComputedValue(); }
                case "OR" -> { return first.getComputedValue() || second.getComputedValue(); }
                case "NOT" -> { return !first.getComputedValue(); }
            }

        }

        // if the expression has already been calculated, return its state
        return ((ComputedExpression) expression).getComputedValue();
    }

    /**
     * @param expression The expression to evaluate
     * @param bits The inputs to evaluate the expression with
     * @return The output the circuit produces with the given inputs
     */
    public boolean evaluateWith(Expression expression, List<Boolean> bits) {
        return evaluate(replaceComputedExpression(expression, bits));
    }

    /**
     * Recurses through an expression to find all ComputedExpressions, which represent switches and hold a state
     * Then, replaces that state with the next item in the provided list of bits
     * The list of bits MUST have the same number of elements as the circuit has switches
     * @param expression The expression to modify
     * @param bits The inputs to set
     * @return The input expression, with the new bits as its input
     */
    private Expression replaceComputedExpression(Expression expression, List<Boolean> bits) {

        if (expression instanceof OperatorExpression operatorExpression) {
            // do nothing, check left and right children
            replaceComputedExpression(operatorExpression.getFirstInput(), bits);
            if (operatorExpression.getSecondInput() != null) { replaceComputedExpression(operatorExpression.getSecondInput(), bits); }
        }

        // replace switch's state
        if (expression instanceof ComputedExpression) {
            ((ComputedExpression) expression).setComputedValue(bits.get(0));
            bits.remove(0);
        }

        return expression;
    }

}