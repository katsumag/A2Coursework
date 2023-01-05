package me.katsumag.A2Coursework.karnaugh_map;

import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.truth_table.TruthTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

        karnaughMap.getInternalState().forEach(row -> System.out.println(row.stream().map(KarnaughMapEntry::getState).collect(Collectors.toList())));

        // get all valid windows
        Windows windowManager = new Windows(karnaughMap);
        List<Window> validWindows = windowManager.getValidWindows();

        //TODO: test valid windows, generate expressions
        List<String> expressions = validWindows.stream().map(Window::getExpression).filter(expression -> !Objects.equals(expression, "()")).toList();

        String finalExpression = String.join(" OR ", expressions);
        System.out.println("Expression: " + finalExpression);

    }

}
