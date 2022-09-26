package me.katsumag.A2Coursework.truth_table;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Provides the inputs needed for a truth table
 * Returns a list of {@link WrappedBitSet}
 */
public class Inputs implements Supplier<List<WrappedBitSet>> {

    // the number to generate binary up to
    private final int maxValue;

    /**
     * @param maxValue the number to generate binary up to
     */
    public Inputs(int maxValue) {
        this.maxValue = maxValue;
    }

    public static void main(String[]  args) {
        new Inputs(6).get().forEach(WrappedBitSet::printBits);
    }

    /**
     * @return {@link List<WrappedBitSet>} containing the binary representations of 0-n
     */
    @Override
    public List<WrappedBitSet> get() {

        List<List<Boolean>> list = new ArrayList<>();

        for (int i = 0; i <= this.maxValue; i++) {
            list.add(DenaryToBinary.convert(i));
        }

        return convertToBitSet(padInputs(list));
    }

    /**
     * @param inputs the 2d list of bits
     * @return a list of bitsets, each representing one number
     */
    private List<WrappedBitSet> convertToBitSet(List<List<Boolean>> inputs) {
        List<WrappedBitSet> outputs = new ArrayList<>();
        inputs.forEach(binary -> {
            WrappedBitSet bitSet = new WrappedBitSet();
            binary.forEach(bitSet::append);
            outputs.add(bitSet);
        });
        return outputs;
    }

    /**
     * @param inputs the 2d list of raw inputs
     * @return the same inputs, padded to the same size
     */
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

    /**
     * @param inputs 2d list of binary numbers
     * @return the size of the maximum length binary number
     */
    private int getMaxSize(List<List<Boolean>> inputs) {
        int maxSize = 0;
        for (List<Boolean> binary : inputs) {
            if (binary.size() > maxSize) { maxSize = binary.size(); }
        }
        return maxSize;
    }

}
