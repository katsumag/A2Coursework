package me.katsumag.A2Coursework.truth_table;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class WrappedBitSet extends BitSet {

    private int position = 0;

    public void append(boolean value) {
        set(position, value);
        position += 1;
    }

    public List<Boolean> getBits() {

        List<Boolean> list = new ArrayList<>();

        for (int i = 0; i < this.position; i++) {
            list.add(get(i));
        }

        return list;
    }

    public void printBits() {
        System.out.println(getBits());
    }

}
