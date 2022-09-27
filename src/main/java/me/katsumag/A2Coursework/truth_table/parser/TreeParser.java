package me.katsumag.A2Coursework.truth_table.parser;

import me.katsumag.A2Coursework.truth_table.lexer.IdentifierToken;
import me.katsumag.A2Coursework.truth_table.lexer.OperatorToken;
import me.katsumag.A2Coursework.truth_table.lexer.Token;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
                Class<?> expressionClass = Class.forName(String.format("me.katsumag.A2Coursework.truth_table.parser.%sExpression", ((OperatorToken) token).getOperationType()));

                // if the buffer is empty, then get from parsedExpressions
                if (bufferedTokens.isEmpty()) {
                    Expression parsedExpression = (Expression) expressionClass.getConstructors()[0].newInstance(parsedExpressions.toArray());
                    parsedExpressions.clear();
                    parsedExpressions.add(parsedExpression);
                } else {
                    // buffer is full and can be used.
                    // I don't know how passing two args into a one arg constructor works, but it does.
                    // thanks, java magic
                    Expression parsedExpression = (Expression) expressionClass.getConstructors()[0].newInstance(
                            new ComputedExpression(bufferedTokens.get(0).getState()),
                            new ComputedExpression(bufferedTokens.get(1).getState())
                    );
                    bufferedTokens.clear();
                    parsedExpressions.add(parsedExpression);
                }
            }
        }
        return parsedExpressions.get(0);

    }
}
