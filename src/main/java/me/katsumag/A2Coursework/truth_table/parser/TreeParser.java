package me.katsumag.A2Coursework.truth_table.parser;

import me.katsumag.A2Coursework.truth_table.lexer.IdentifierToken;
import me.katsumag.A2Coursework.truth_table.lexer.OperatorToken;
import me.katsumag.A2Coursework.truth_table.lexer.Token;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * Handles parsing the tokens produced by the lexer into one expression that the evaluator can run
 */
public class TreeParser {

    /**
     * @param tokens The list of tokens to parse
     * @return One expression which is the representation of the placed circuit
     */
    public Expression parse(List<Token> tokens) {

        Stack<Expression> stack = new Stack<>();

        tokens.forEach(token -> {
            if (token instanceof OperatorToken operatorToken) {
                // Handle gates with two inputs, so AND and OR
                if (Objects.equals(operatorToken.getOperationType(), "AND") || Objects.equals(operatorToken.getOperationType(), "OR")) {

                    // pop inputs in reverse order
                    Expression first = stack.pop();
                    Expression second = stack.pop();

                    stack.push(
                            new OperatorExpression(
                                    OperatorExpressionType.valueOf(operatorToken.getOperationType()),
                                    second,
                                    first
                            )
                    );

                } else {
                    // deal with NOT gates
                    stack.push(
                            new OperatorExpression(OperatorExpressionType.NOT, stack.pop(), null)
                    );
                }
            } else {
                stack.push(new ComputedExpression(((IdentifierToken) token).getState()));
            }
        });

        return stack.pop();

    }

}