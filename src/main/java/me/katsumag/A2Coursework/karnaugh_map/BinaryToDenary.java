package me.katsumag.A2Coursework.karnaugh_map;

import java.util.List;

public class BinaryToDenary {


    public static int convert(List<Boolean> binary) {

        StringBuilder sb = new StringBuilder();

        binary.forEach(bit -> sb.append((bit) ? 1 : 0));

        return Integer.parseInt(sb.toString(), 2);
    }



}
