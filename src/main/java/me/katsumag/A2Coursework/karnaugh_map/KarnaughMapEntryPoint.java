package me.katsumag.A2Coursework.karnaugh_map;

import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.truth_table.TruthTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

        // get window templates
        List<Window> windows = new Windows(karnaughMap).getDefaultPositionWindows();

        // get all valid windows
        int mapYSize = karnaughMap.getInternalState().size();
        int mapXSize = karnaughMap.getInternalState().get(0).size();

        List<List<List<KarnaughMapEntry>>> validWindows = new ArrayList<>();
        // Tries the window in every possible place, even if it's unnecessary
        windows.forEach(window -> {
            while (window.getCurrentY() < mapYSize) {
                while (window.getCurrentX() < mapXSize) {
                    if (window.isValid()) {
                        // add a copy of the window's internal state, since the object it reused
                        validWindows.add(new ArrayList(window.getWindow()));
                    }
                    window.shiftRight();
                }
                window.shiftUp();
                window.resetX();
            }
        });

        //TODO: test valid windows, generate expressions
    }

}
