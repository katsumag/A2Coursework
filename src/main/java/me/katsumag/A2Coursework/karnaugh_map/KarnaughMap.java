package me.katsumag.A2Coursework.karnaugh_map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KarnaughMap {

    private List<List<Boolean>> internalState;
    private int mapSize;

    public KarnaughMap(Map<List<Boolean>, Boolean> truthTable) {
        this.internalState = getEmptyMap((int) Math.sqrt(truthTable.size()));
        this.mapSize = this.internalState.size() * this.internalState.get(0).size();
        populateMap(truthTable);
    }

    public int getMapSize() { return mapSize; }

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

    private void populateMap(Map<List<Boolean>, Boolean> truthTable) {
        for (Map.Entry<List<Boolean>, Boolean> entrySet : truthTable.entrySet()) {

            // because the map is already filled with 0s, only need to set 1s
            if (! entrySet.getValue()) { continue; }

            List<Boolean> binaryY = entrySet.getKey().subList(0, entrySet.getKey().size() / 2);
            List<Boolean> binaryX = entrySet.getKey().subList(entrySet.getKey().size() / 2, entrySet.getKey().size());

            int y = BinaryToDenary.convert(binaryY);
            int x = BinaryToDenary.convert(binaryX);

            this.internalState.get(y).set(x, entrySet.getValue());
        }
    }

    public void sortByGrayCode(List<Integer> grayCode) {
        this.internalState = new MapSorter().sortMapIntoGrayCodeOrder(this.internalState, grayCode);
    }

}
