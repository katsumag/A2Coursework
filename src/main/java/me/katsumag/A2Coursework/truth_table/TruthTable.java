package me.katsumag.A2Coursework.truth_table;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import me.katsumag.A2Coursework.karnaugh_map.BinaryToDenary;
import me.katsumag.A2Coursework.truth_table.evaluator.TreeEvaluator;
import me.katsumag.A2Coursework.truth_table.lexer.IdentifierToken;
import me.katsumag.A2Coursework.truth_table.lexer.Token;
import me.katsumag.A2Coursework.truth_table.lexer.TreeLexer;
import me.katsumag.A2Coursework.truth_table.parser.Expression;
import me.katsumag.A2Coursework.truth_table.parser.TreeParser;

import java.util.*;

public class TruthTable {

    public Map<List<Boolean>, Boolean> create(MouseEvent event) {

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
        Map<List<Boolean>, Boolean> table = new HashMap<>();
        for (List<Boolean> inputs: new Inputs((int) Math.pow(numOfSwitches, 2)).get()) {
            table.put(inputs, treeEvaluator.evaluateWith(expression, new ArrayList<>(inputs)));
        }

        table.forEach((inputs, output) -> {
            System.out.println(inputs + " : " + output);
        });

        // display truth table
        TableView guiTable = new TableView<>();
        guiTable.setEditable(true);

        // create named columns for each switch
        for (int i = 0; i < numOfSwitches; i++) {
            TableColumn columnHeader = new TableColumn<>("" + (char) (65 + i));
            columnHeader.setCellValueFactory(new MapValueFactory<>("" + (char) (65 + i)));
            guiTable.getColumns().add(columnHeader);
        }

        // create column for the output
        TableColumn resultColumn = new TableColumn<>("Q");
        resultColumn.setCellValueFactory(new MapValueFactory<>("Q"));
        guiTable.getColumns().add(resultColumn);

        // add truth table to the table by sorting map by input value, then converting the map into the form ["Switch": value]
        guiTable.setItems(FXCollections.observableList(table.entrySet().stream().sorted((first, second) -> {
            int firstValue = BinaryToDenary.convert(first.getKey());
            int secondValue = BinaryToDenary.convert(second.getKey());
            if (firstValue < secondValue) return -1; else return 1;
        }).map(entry -> {
            Map<String, Boolean> rows = new HashMap<>();
            for (int i = 0; i < entry.getKey().size(); i++) {
                rows.put("" + (char) (65 + i), entry.getKey().get(i));
            }
            rows.put("Q", entry.getValue());
            return rows;
        }).toList()));

        // create and show new window
        Stage tableStage = new Stage();
        Scene tableScene = new Scene(guiTable, 400, 400);
        tableStage.setScene(tableScene);
        tableStage.setTitle("A2Coursework | Truth Table");
        tableStage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream("images/icon.png")));
        tableStage.show();

        return table;
    }

}
