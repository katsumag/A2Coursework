package me.katsumag.A2Coursework.truth_table.parser;

import me.katsumag.A2Coursework.truth_table.lexer.IdentifierToken;
import me.katsumag.A2Coursework.truth_table.lexer.OperatorToken;
import me.katsumag.A2Coursework.truth_table.lexer.Token;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class TreeParser {

    public Expression parse(List<Token> tokens) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Stack<Expression> bufferedTokens = new Stack<>();

        tokens.forEach(token -> {
            if (token instanceof IdentifierToken identifierToken) {
                bufferedTokens.push(new ComputedExpression(identifierToken.getState()));
                return;
            }

            OperatorToken operatorToken = ((OperatorToken) token);

            System.out.println("bufferedTokens = " + bufferedTokens.stream().map(Expression::getExpressionType).toList());
            System.out.println("operatorToken = " + operatorToken.getOperationType());

            if (!Objects.equals(operatorToken.getOperationType(), "NOT")) {
                bufferedTokens.push(
                        new OperatorExpression(
                                OperatorExpressionType.valueOf(operatorToken.getOperationType()),
                                bufferedTokens.pop(),
                                bufferedTokens.pop()
                        )
                );
            } else {
                bufferedTokens.push(
                        new OperatorExpression(
                                OperatorExpressionType.NOT, bufferedTokens.pop()
                        )
                );
            }

        });

        return bufferedTokens.pop();

    }
}
