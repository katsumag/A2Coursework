package me.katsumag.A2Coursework.truth_table;

import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.truth_table.evaluator.TreeEvaluator;
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
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
