package me.katsumag.A2Coursework.karnaugh_map;

import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.truth_table.TruthTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        //TODO: find all valid windows, generate expressions
    }

}
