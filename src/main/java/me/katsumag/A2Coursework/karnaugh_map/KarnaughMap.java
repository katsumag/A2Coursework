package me.katsumag.A2Coursework.karnaugh_map;

import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.truth_table.TruthTable;

import java.util.*;
import java.util.stream.Collectors;

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

        //TODO: gray code order, expression generation

        int grayCodeIteration = (int) (Math.sqrt(getSizeOfMap(emptyMap)) / 2);
        List<List<Boolean>> rawGrayCode = getGrayCode(grayCodeIteration);
        List<Integer> grayCode = convertGrayCodeToInts(rawGrayCode);
        List<List<Boolean>> sortedMap = sortMapIntoGrayCodeOrder(emptyMap, grayCode);
        sortedMap.forEach(System.out::println);
    }

    private List<List<Boolean>> sortMapIntoGrayCodeOrder(List<List<Boolean>> map, List<Integer> grayCode) {
        return sortYByGrayCode(sortXByGrayCode(map, grayCode), grayCode);
    }

    private int getSizeOfMap(List<List<Boolean>> map) {
        return map.size() * map.get(0).size();
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

    private List<Integer> convertGrayCodeToInts(List<List<Boolean>> grayCode) {
        return grayCode.stream().map(BinaryToDenary::convert).collect(Collectors.toList());
    }

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

    /*
    Get next gray code sequence from previous by:
    - reversing a copy of the previous codes
    - adding 1 in front of each of them
    - adding 0 in front of each of the previous codes
    - collecting the reversed and previous codes into one list
     */
    private List<List<Boolean>> generateNextGrayCode(List<List<Boolean>> previous) {
        // reverse previous - need to copy it
        List<List<Boolean>> reflected = new ArrayList<>();
        previous.forEach(code -> reflected.add(new ArrayList<>(code)));
        Collections.reverse(reflected);

        previous.forEach(code -> code.add(0, false));

        reflected.forEach(code -> code.add(0, true));

        previous.addAll(reflected);
        return previous;
    }

    /*
    creates gray codes up to (and including) iteration n, see https://en.wikipedia.org/wiki/Gray_code
     */
    private List<List<Boolean>> getGrayCode(final int nIteration) {
        // create base case n=1, [0, 1]
        List<List<Boolean>> grayCode = new ArrayList<>(Arrays.asList(toMutableList(List.of(false)), toMutableList(List.of(true))));
        if (nIteration <= 1) { return  grayCode; }

        for (int i = 0; i < (nIteration - 1); i++) {
            grayCode = generateNextGrayCode(grayCode);
        }

        return grayCode;

    }

    private <T> List<T> toMutableList (List<T> immutableList) {
        return new ArrayList<>(immutableList);
    }

    public static void main(String[] args) {
        System.out.println(new KarnaughMap().getGrayCode(3));
    }

}
