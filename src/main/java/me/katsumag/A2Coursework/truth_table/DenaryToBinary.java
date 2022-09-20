package me.katsumag.A2Coursework.truth_table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DenaryToBinary {

    public static WrappedBitSet convert(int denary) {
        DenaryToBinary denaryToBinary = new DenaryToBinary();
        return denaryToBinary.reverseToBitSet(denaryToBinary.getRawBinary(denary));
    }

    private WrappedBitSet reverseToBitSet(List<Boolean> rawBinary) {

        // create new bit set
        WrappedBitSet bitSet = new WrappedBitSet();

        // reverse generated binary
        Collections.reverse(rawBinary);

        // add to bit set
        rawBinary.forEach(bitSet::append);

        return bitSet;
    }

    private List<Boolean> getRawBinary(int denary) {

        // create new list
        List<Boolean> rawBinary = new ArrayList<>();

        // generate binary, but in reverse
        while (denary != 0) {
            int binary = denary % 2;
            rawBinary.add(binary != 0);
            denary = denary / 2;
        }

        return rawBinary;
    }

}
