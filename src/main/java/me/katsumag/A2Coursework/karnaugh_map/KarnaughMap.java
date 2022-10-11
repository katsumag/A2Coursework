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

        truthTable.forEach((inputs, output) -> {
            List<Boolean> binaryY = inputs.subList(0, inputs.size() / 2);
            List<Boolean> binaryX = inputs.subList(inputs.size() / 2, inputs.size());

            int y = BinaryToDenary.convert(binaryY);
            int x = BinaryToDenary.convert(binaryX);

            emptyMap.get(y).set(x, output);
        });

    }

    private List<List<Boolean>> getEmptyMap(int inputCount) {
        List<List<Boolean>> emptyMap =  new ArrayList<>();

        for (int i = 0; i < inputCount; i++) {
            emptyMap.add(new ArrayList<>());
        }

        return emptyMap;
    }

}
