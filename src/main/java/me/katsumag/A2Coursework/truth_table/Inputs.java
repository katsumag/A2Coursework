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
        List<WrappedBitSet> list = new ArrayList<>();

        list.add(getZero());

        for (int i = 0; i < this.maxValue; i++) {
            list.add(DenaryToBinary.convert(i));
        }

        return list;
    }

    private WrappedBitSet getZero() {
        WrappedBitSet zero = new WrappedBitSet();
        zero.append(false);
        return zero;
    }

}
