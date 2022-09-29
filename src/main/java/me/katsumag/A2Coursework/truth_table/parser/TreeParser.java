package me.katsumag.A2Coursework.truth_table.parser;

import me.katsumag.A2Coursework.truth_table.lexer.IdentifierToken;
import me.katsumag.A2Coursework.truth_table.lexer.OperatorToken;
import me.katsumag.A2Coursework.truth_table.lexer.Token;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeParser {

    public Expression parse(List<Token> tokens) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {

        List<Expression> parsedExpressions = new ArrayList<>();
        List<IdentifierToken> bufferedTokens = new ArrayList<>();

        for (Token token : tokens) {
            // add to buffer if there's nothing to do
            if (! (token instanceof OperatorToken)) {
                bufferedTokens.add(((IdentifierToken) token));
            } else {
                // use reflection to get XExpression class based on current OperatorToken
                Class<?> expressionClass = Class.forName("me.katsumag.A2Coursework.truth_table.parser.OperatorExpression");

                // if the buffer is empty, then get from parsedExpressions
                // also will be an OperatorExpression
                if (bufferedTokens.isEmpty()) {

                    // handle operator being a NOT gate and so there is no relevant second input
                    Expression second;
                    if (Objects.equals(((OperatorToken) token).getOperationType(), "NOT")) {
                        second = null;
                    } else {
                        second = parsedExpressions.get(1);
                    }

                    // produce expression
                    Expression parsedExpression = (Expression) expressionClass.getConstructors()[0].newInstance(OperatorExpressionType.valueOf(((OperatorToken) token).getOperationType()), parsedExpressions.get(0), second);
                    parsedExpressions.clear();
                    parsedExpressions.add(parsedExpression);
                } else {
                    // buffer is full and can be used.

                    // handle not having a second input if it's a NOT gate
                    ComputedExpression second;
                    if (Objects.equals(((OperatorToken) token).getOperationType(), "NOT")) {
                        second = null;
                    } else { second = new ComputedExpression(bufferedTokens.get(1).getState()); }

                    // produce expression
                    OperatorExpression parsedExpression = new OperatorExpression(
                            OperatorExpressionType.valueOf(((OperatorToken) token).getOperationType()),
                            new ComputedExpression(bufferedTokens.get(0).getState()),
                            second
                    );

                    bufferedTokens.clear();
                    parsedExpressions.add(parsedExpression);
                }
            }
        }
        return parsedExpressions.get(0);

    }
}
