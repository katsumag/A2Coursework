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

    public static void main(String[] args) {
        List<List<Boolean>> codes = new ArrayList<>();
        List<Boolean> one = new ArrayList<>();
        one.add(false);
        List<Boolean> two = new ArrayList<>();
        two.add(true);
        codes.add(one);
        codes.add(two);
        System.out.println(new KarnaughMap().generateNextGrayCode(codes));
    }

}
