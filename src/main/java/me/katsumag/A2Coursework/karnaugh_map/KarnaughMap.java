package me.katsumag.A2Coursework.karnaugh_map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KarnaughMap {

    private List<List<KarnaughMapEntry>> internalState;

    private final int mapXSize, mapYSize, mapSize;

    public KarnaughMap(Map<List<Boolean>, Boolean> truthTable) {
        this.internalState = getEmptyMap((int) Math.sqrt(truthTable.size()));
        this.mapYSize = this.internalState.size();
        this.mapXSize = this.internalState.get(0).size();
        this.mapSize = this.mapXSize * this.mapYSize;
        populateMap(truthTable);
    }

    public int getMapSize() { return mapSize; }

    public int getMapXSize() { return mapXSize; }
    public int getMapYSize() { return mapYSize; }

    public List<List<KarnaughMapEntry>> getInternalState() { return this.internalState; }

    protected void setInternalState(List<List<KarnaughMapEntry>> newState) { this.internalState = newState; }

    /*
     * Construct mutable empty map from the number of switches.
     * Map size is 2^(numberOfSwitches)
     */
    private List<List<KarnaughMapEntry>> getEmptyMap(int inputCount) {
        List<List<KarnaughMapEntry>> emptyMap =  new ArrayList<>();

        for (int i = 0; i < inputCount; i++) {
            // needed because set requires that index to exist
            List<KarnaughMapEntry> defaultRow = new ArrayList<>();
            for (int j = 0; j < inputCount; j++) {
                defaultRow.add(new KarnaughMapEntry(false));
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

            this.internalState.get(x).set(y, new KarnaughMapEntry(entrySet.getValue()));
        }
    }

    public void sortByGrayCode(List<Integer> grayCode) {
        new MapSorter().sortMapIntoGrayCodeOrder(this, grayCode);
    }

}
