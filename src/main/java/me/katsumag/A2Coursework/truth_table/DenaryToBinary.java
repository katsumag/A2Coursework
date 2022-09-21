package me.katsumag.A2Coursework.truth_table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DenaryToBinary {

    public static List<Boolean> convert(int denary) {

        // create new list
        List<Boolean> rawBinary = new ArrayList<>();

        // generate binary, but in reverse
        while (denary != 0) {
            int binary = denary % 2;
            rawBinary.add(binary != 0);
            denary = denary / 2;
        }

        // reverse to actual binary number
        Collections.reverse(rawBinary);

        return rawBinary;
    }
}
