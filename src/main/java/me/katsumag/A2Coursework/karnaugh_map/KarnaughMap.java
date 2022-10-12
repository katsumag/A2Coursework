package me.katsumag.A2Coursework.karnaugh_map;

import javafx.scene.input.MouseEvent;
import me.katsumag.A2Coursework.truth_table.TruthTable;

import java.util.*;

public class KarnaughMap {

    public void create(MouseEvent event) {
        Map<List<Boolean>, Boolean> truthTable = new TruthTable().create(event);

        if (truthTable == null) { return; }

        List<List<Boolean>> emptyMap = getEmptyMap((int) Math.sqrt(truthTable.size()));

        truthTable.forEach((inputs, output) -> {
            List<Boolean> binaryY = inputs.subList(0, inputs.size() / 2);
            List<Boolean> binaryX = inputs.subList(inputs.size() / 2, inputs.size());

            int y = BinaryToDenary.convert(binaryY);
            int x = BinaryToDenary.convert(binaryX);

            emptyMap.get(y).set(x, output);
        });

        emptyMap.forEach(System.out::println);

        //TODO: gray code order, expression generation

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

    private List<List<Boolean>> getGrayCode(int nIteration) {
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
