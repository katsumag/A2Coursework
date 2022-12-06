package me.katsumag.A2Coursework.truth_table.parser;

import me.katsumag.A2Coursework.truth_table.lexer.IdentifierToken;
import me.katsumag.A2Coursework.truth_table.lexer.OperatorToken;
import me.katsumag.A2Coursework.truth_table.lexer.Token;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class TreeParser {

    public Expression parse(List<Token> tokens) {

        Stack<Expression> stack = new Stack<>();

        tokens.forEach(token -> {
            if (token instanceof OperatorToken operatorToken) {
                if (Objects.equals(operatorToken.getOperationType(), "AND") || Objects.equals(operatorToken.getOperationType(), "OR")) {

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