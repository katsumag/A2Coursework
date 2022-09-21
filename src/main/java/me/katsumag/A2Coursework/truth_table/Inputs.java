package me.katsumag.A2Coursework.truth_table;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Provides the inputs needed for a truth table
 * Returns a list of {@link WrappedBitSet}
 */
public class Inputs implements Supplier<List<WrappedBitSet>> {

    private final int maxValue;

    public Inputs(int maxValue) {
        this.maxValue = maxValue;
    }

    public static void main(String[]  args) {
        new Inputs(4).get().forEach(WrappedBitSet::printBits);
    }

    @Override
    public List<WrappedBitSet> get() {

        List<List<Boolean>> list = new ArrayList<>();

        for (int i = 0; i <= this.maxValue; i++) {
            list.add(DenaryToBinary.convert(i));
        }

        return convertToBitSet(padInputs(list));
    }

    private List<WrappedBitSet> convertToBitSet(List<List<Boolean>> inputs) {
        List<WrappedBitSet> outputs = new ArrayList<>();
        inputs.forEach(binary -> {
            WrappedBitSet bitSet = new WrappedBitSet();
            binary.forEach(bitSet::append);
            outputs.add(bitSet);
        });
        return outputs;
    }

    private List<List<Boolean>> padInputs(List<List<Boolean>> inputs) {
        int maxSize = getMaxSize(inputs);

        inputs.forEach(binary -> {
            if (binary.size() < maxSize) {
                // calculate difference needed to pad
                int padAmount = maxSize - binary.size();
                for (int i = 0; i < padAmount; i++) {
                    // pad left with 0s
                    binary.add(0, false);
                }
            }
        });

        return inputs;
    }

    private int getMaxSize(List<List<Boolean>> inputs) {
        int maxSize = 0;
        for (List<Boolean> binary : inputs) {
            if (binary.size() > maxSize) { maxSize = binary.size(); }
        }
        return maxSize;
    }

}
