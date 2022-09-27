package me.katsumag.A2Coursework.truth_table.lexer;

import me.katsumag.A2Coursework.components.CircuitComponent;
import me.katsumag.A2Coursework.components.CircuitComponentType;
import me.katsumag.A2Coursework.truth_table.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeLexer {

    private void traversePostOrder(Node tree, List<CircuitComponent> list) {
        if (tree == null) { return; }

        traversePostOrder(tree.getLeftChild(), list);
        traversePostOrder(tree.getRightChild(), list);

        list.add(tree.getCircuitComponent());

    }

    public List<Token> lex(Node tree) {

        // get components in postfix notation
        List<CircuitComponent> postfixComponents = new ArrayList<>();
        traversePostOrder(tree, postfixComponents);

        // filter the ending lamp from the components
        postfixComponents.removeIf(component -> component.getType() == CircuitComponentType.LAMP);

        // lex components into Identifiers and Operators
        List<Token> tokens = new ArrayList<>();
        postfixComponents.forEach(component -> {
            if (component.getType() == CircuitComponentType.SWITCH) {
                tokens.add(new IdentifierToken(component));
            } else {
                tokens.add(new OperatorToken(component.getType().getName()));
            }
        });

        return tokens;
    }

}
