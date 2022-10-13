package me.katsumag.A2Coursework.karnaugh_map;

import java.util.ArrayList;
import java.util.List;

public class MapSorter {

    public List<List<Boolean>> sortMapIntoGrayCodeOrder(List<List<Boolean>> map, List<Integer> grayCode) {
        return sortYByGrayCode(sortXByGrayCode(map, grayCode), grayCode);
    }



    // sorts the map so that its columns are in gray code order. Briefly tested, seems to work okay - need to do more later
    // the map passed in MUST have its rows in gray code order already.
    private List<List<Boolean>> sortYByGrayCode(List<List<Boolean>> map, List<Integer> codes) {
        // go through x by non-gray code order, since it's always x-sorted before it gets here
        // iterating through x with gray code indexes will reverse its effects

        // construct new map
        List<List<Boolean>> sortedMap = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < codes.size(); rowIndex++) {
            // set up current and new rows
            List<Boolean> currentRow = map.get(rowIndex);
            List<Boolean> newRow = prefillRow(codes.size());

            // sets newRow[colIndex] to currentRow[codes[colIndex]]
            for (int colIndex = 0; colIndex < codes.size(); colIndex++) {
                newRow.set(colIndex, currentRow.get(codes.get(colIndex)));
            }

            sortedMap.add(newRow);

        }
        return sortedMap;
    }

    // returns a list of <size> full of false, useful later on because set(index, data) requires that index to already exist
    private List<Boolean> prefillRow(int size) {
        List<Boolean> row = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            row.add(false);
        }
        return row;
    }

    private List<List<Boolean>> sortXByGrayCode(List<List<Boolean>> map, List<Integer> grayCode) {
        List<List<Boolean>> newSortedMap = new ArrayList<>();
        grayCode.forEach(index -> newSortedMap.add(map.get(index)));
        return newSortedMap;
    }

}
