package me.katsumag.A2Coursework.karnaugh_map;

import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.truth_table.TruthTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KarnaughMap {

    public void create(MouseEvent event) {
        Map<List<Boolean>, Boolean> truthTable = new TruthTable().create(event);

        if (truthTable == null) { return; }

        List<List<Boolean>> emptyMap = getEmptyMap((int) Math.sqrt(truthTable.size()));

        for (Map.Entry<List<Boolean>, Boolean> entrySet : truthTable.entrySet()) {

            // because the map is already filled with 0s, only need to set 1s
            if (! entrySet.getValue()) { continue; }

            List<Boolean> binaryY = entrySet.getKey().subList(0, entrySet.getKey().size() / 2);
            List<Boolean> binaryX = entrySet.getKey().subList(entrySet.getKey().size() / 2, entrySet.getKey().size());

            int y = BinaryToDenary.convert(binaryY);
            int x = BinaryToDenary.convert(binaryX);

            emptyMap.get(y).set(x, entrySet.getValue());
        }

        // get gray code
        int grayCodeIteration = (int) (Math.sqrt(getSizeOfMap(emptyMap)) / 2);
        List<Integer> grayCode = new GrayCode().get(grayCodeIteration);

        // sort map into gray code order
        List<List<Boolean>> sortedMap = new MapSorter().sortMapIntoGrayCodeOrder(emptyMap, grayCode);
        sortedMap.forEach(System.out::println);

        //TODO: find all valid windows, generate expressions
    }

    /*
     * Construct mutable empty map from the number of switches.
     * Map size is 2^(numberOfSwitches)
     */
    private List<List<Boolean>> getEmptyMap(int inputCount) {
        List<List<Boolean>> emptyMap =  new ArrayList<>();

        for (int i = 0; i < inputCount; i++) {
            // needed because set requires that index to exist
            List<Boolean> defaultRow = new ArrayList<>();
            for (int j = 0; j < inputCount; j++) {
                defaultRow.add(false);
            }
            emptyMap.add(defaultRow);
        }

        return emptyMap;
    }

    private int getSizeOfMap(List<List<Boolean>> map) {
        return map.size() * map.get(0).size();
    }

}
