package me.katsumag.A2Coursework.truth_table;

import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.truth_table.evaluator.TreeEvaluator;
import me.katsumag.A2Coursework.truth_table.lexer.IdentifierToken;
import me.katsumag.A2Coursework.truth_table.lexer.Token;
import me.katsumag.A2Coursework.truth_table.lexer.TreeLexer;
import me.katsumag.A2Coursework.truth_table.parser.Expression;
import me.katsumag.A2Coursework.truth_table.parser.TreeParser;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class TruthTable {

    public void create(MouseEvent event) {

        TreeGenerator treeGenerator = new TreeGenerator();
        TreeLexer treeLexer = new TreeLexer();
        TreeParser treeParser = new TreeParser();
        TreeEvaluator treeEvaluator = new TreeEvaluator();

        // construct + print tree
        Node tree = treeGenerator.getTreeFromPlacedComponents(event);
        Node.print2DUtil(tree, 0);

        // lex tree
        List<Token> tokens = treeLexer.lex(tree);
        System.out.println(tokens);

        // parse tree
        try {
            Expression expression = treeParser.parse(tokens);
            System.out.println(expression);
            // evaluate tree
            System.out.println(treeEvaluator.evaluate(expression));

            // inputs calculated correctly, but there are less bits (3) at 4, than
            // there are switches (4), therefore we run out of bits to give the last switch
            // can be fixed by padding inputs again.
            // This is irrelevant because the number passed to Inputs will be (num of switches)^2, which will
            // mean this problem won't exist - leaving notes here though for writeup

            long numOfSwitches = tokens.stream().filter(token -> token instanceof IdentifierToken).count();

            // get inputs from 0 to ((numOfSwitches)^2) -1
            // eg for numOfSwitches = 4 inputs will be 0 to 15
            for (List<Boolean> inputs: new Inputs((int) Math.pow(numOfSwitches, 2)).get()) {
                System.out.println(inputs + " : " + treeEvaluator.evaluateWith(expression, inputs));
            }

        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
