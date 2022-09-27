package me.katsumag.A2Coursework.truth_table;

import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.truth_table.lexer.Token;
import me.katsumag.A2Coursework.truth_table.lexer.TreeLexer;

import java.util.List;

public class TruthTable {

    public void create(MouseEvent event) {

        TreeGenerator treeGenerator = new TreeGenerator();
        TreeLexer treeLexer = new TreeLexer();

        // construct + print tree
        Node tree = treeGenerator.getTreeFromPlacedComponents(event);
        Node.print2DUtil(tree, 0);

        List<Token> tokens = treeLexer.lex(tree);
        System.out.println(tokens);
    }

}
