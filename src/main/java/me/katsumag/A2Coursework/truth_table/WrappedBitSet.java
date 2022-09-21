package me.katsumag.A2Coursework.truth_table;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

// Wrapped BitSet to provide append, because it would've been too hard to manage the state of multiple bitsets otherwise
public class WrappedBitSet extends BitSet {

    // current position in the bitset
    private int position = 0;

    // set a value at the current index
    public void append(boolean value) {
        set(position, value);
        position += 1;
    }

    // get all the bits that I know have been set, which is up to "position"
    public List<Boolean> getBits() {

        List<Boolean> list = new ArrayList<>();

        for (int i = 0; i < this.position; i++) {
            list.add(get(i));
        }

        return list;
    }

    // Function to simplify printing a BitSet. Not really necessary, but nice anyway
    public void printBits() {
        System.out.println(getBits());
    }

}
