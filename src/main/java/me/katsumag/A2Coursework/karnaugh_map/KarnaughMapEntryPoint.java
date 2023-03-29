package me.katsumag.A2Coursework.karnaugh_map;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.katsumag.A2Coursework.truth_table.DenaryToBinary;
import me.katsumag.A2Coursework.truth_table.TruthTable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Called by the main program when the karnaugh map button is pressed
 * Creates a truth table, then turns that into a Karnaugh map, solves it, and displays the result in new windows
 */
public class KarnaughMapEntryPoint {
    public void create(MouseEvent event) {
        Map<List<Boolean>, Boolean> truthTable = new TruthTable().create(event);

        if (truthTable == null) { return; }

        KarnaughMap karnaughMap = new KarnaughMap(truthTable);

        // get gray code
        int grayCodeIteration = (int) (Math.sqrt(karnaughMap.getMapSize()) / 2);
        List<Integer> grayCode = new GrayCode().get(grayCodeIteration);

        // sort map into gray code order
        karnaughMap.sortByGrayCode(grayCode);

        // get all valid windows
        Windows windowManager = new Windows(karnaughMap);
        List<Window> validWindows = windowManager.getValidWindows();

        // get all expressions
        List<String> expressions = validWindows.stream().map(Window::getExpression).filter(expression -> !Objects.equals(expression, "()")).toList();

        String finalExpression = String.join(" OR ", expressions);
        System.out.println("Expression: " + finalExpression);

        // display karnaugh map and the resulting expression
        TableView guiMap = new TableView<>();

        // set headers
        TableColumn rowColumn = new TableColumn<>("");
        rowColumn.setCellValueFactory(new MapValueFactory<>("rowHeader"));
        guiMap.getColumns().add(rowColumn);

        grayCode.forEach(header -> {
            String headerValue = String.join("", boolsToHeader(DenaryToBinary.convert(header), grayCodeIteration));
            TableColumn column = new TableColumn<>(headerValue);
            column.setCellValueFactory(new MapValueFactory<>(headerValue));
            guiMap.getColumns().add(column);
        });

        // set items into the table
        guiMap.setItems(FXCollections.observableList(karnaughMap.getInternalState().stream().map(row -> {

            HashMap<String, String> map = new HashMap<>();

            map.put("rowHeader", String.join("", boolsToHeader(DenaryToBinary.convert(grayCode.get(karnaughMap.getInternalState().indexOf(row))), grayCodeIteration)));

            for (int i = 0; i < row.size(); i++) {
                map.put(String.join("", boolsToHeader(DenaryToBinary.convert(grayCode.get(i)), grayCodeIteration)), "" + row.get(i).getState());
            }
            return map;

        }).toList()));

        // resize map
        guiMap.setFixedCellSize(25);
        guiMap.prefHeightProperty().bind(guiMap.fixedCellSizeProperty().multiply(Bindings.size(guiMap.getItems()).add(1.01)));
        guiMap.minHeightProperty().bind(guiMap.prefHeightProperty());
        guiMap.maxHeightProperty().bind(guiMap.prefHeightProperty());

        // create and show map + expression
        Stage mapStage = new Stage();
        VBox mapPane = new VBox();
        mapPane.getChildren().addAll(guiMap, new Label("Simplified Expression: \n" + finalExpression));
        Scene mapScene = new Scene(mapPane, 400, 400);
        mapStage.setScene(mapScene);
        mapStage.setTitle("A2Coursework | Karnaugh Map");
        mapStage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream("images/icon.png")));
        mapStage.show();
    }

    private List<String> boolsToHeader(List<Boolean> bools, int headerSize) {
        return padHeader(bools, headerSize).stream().map(bool -> bool ? "1" : "0").toList();
    }

    private List<Boolean> padHeader(List<Boolean> header, int size) {
        for (int i = 0; i < size - header.size(); i++) {
            header.add(0, false);
        }
        return header;
    }

}
