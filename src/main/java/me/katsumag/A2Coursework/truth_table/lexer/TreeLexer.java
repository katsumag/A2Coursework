package me.katsumag.A2Coursework.truth_table.lexer;

import me.katsumag.A2Coursework.components.CircuitComponent;
import me.katsumag.A2Coursework.components.CircuitComponentType;
import me.katsumag.A2Coursework.truth_table.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles tokenizing the tree of circuit components into a form understood by the parser
 */
public class TreeLexer {

    /**
     * Performs a post order traversal on the given tree into the given list.
     * That last part is REALLY not ideal, but hey, it works
     * This returns a list of CircuitComponents in postfix (reverse polish) notation
     * @param tree Tree to traverse
     * @param list List to hold the result of said traversal
     */
    private void traversePostOrder(Node tree, List<CircuitComponent> list) {
        if (tree == null) { return; }

        traversePostOrder(tree.getLeftChild(), list);
        traversePostOrder(tree.getRightChild(), list);

        list.add(tree.getCircuitComponent());

    }

    /**
     * Handles lexing the tree into a list of tokens for the parser
     * @param tree Tree to lex
     * @return List of tokens for the parser - in postfix notation
     */
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
