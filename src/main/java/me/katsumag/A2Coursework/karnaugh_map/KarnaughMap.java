package me.katsumag.A2Coursework.karnaugh_map;

import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.truth_table.TruthTable;

import javax.imageio.plugins.tiff.ExifInteroperabilityTagSet;
import java.util.*;
import java.util.stream.Collectors;

public class KarnaughMap {

    public void create(MouseEvent event) {
        Map<List<Boolean>, Boolean> truthTable = new TruthTable().create(event);

        if (truthTable == null) { return; }

        System.out.println("passed null check");

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

        System.out.println("Added entries to map");

        emptyMap.forEach(System.out::println);

        //TODO: gray code order, expression generation

        System.out.println("doing new stuff");
        int grayCodeIteration = (int) (Math.sqrt(getSizeOfMap(emptyMap)) / 2);
        List<List<Boolean>> rawGrayCode = getGrayCode(grayCodeIteration);
        List<Integer> grayCode = convertGrayCodeToInts(rawGrayCode);
        emptyMap = sortXByGrayCode(emptyMap, grayCode);
        System.out.println("X sorted");
        emptyMap.forEach(System.out::println);
        emptyMap = sortYByGrayCode(emptyMap, grayCode);
        System.out.println("Sorted maps");
        emptyMap.forEach(System.out::println);
        System.out.println("Finished");
        sortYByGradeCode(emptyMap, grayCode);
        //return emptyMap;
    }

    private int getSizeOfMap(List<List<Boolean>> map) {
        return map.size() * map.get(0).size();
    }

    private List<List<Boolean>> sortYByGrayCode(List<List<Boolean>> map, List<Integer> grayCode) {
        List<List<Boolean>> newSortedMap = new ArrayList<>();
        for (int i = 0; i < grayCode.size(); i++) {
            newSortedMap.add(new ArrayList<>());
        }

        grayCode.forEach(code -> map.forEach(row -> newSortedMap.get(code).add(row.get(code))));

        return newSortedMap;
    }

    private void sortYByGradeCode(List<List<Boolean>> map, List<Integer> grayCode) {
        List<List<Boolean>> newSortedMap = new ArrayList<>();
        map.forEach(row -> {
            List<Boolean> newRow = new ArrayList<>();
            for (int i = 0; i < grayCode.size(); i++) {
                newRow.add(grayCode.get(i), row.get(i));
            }
            newSortedMap.add(newRow);
        });
        newSortedMap.forEach(System.out::println);
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
