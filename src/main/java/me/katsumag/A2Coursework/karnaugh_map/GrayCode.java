package me.katsumag.A2Coursework.karnaugh_map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GrayCode {

    public List<Integer> get(int nIteration) {
        return convertGrayCodeToInts(getGrayCode(nIteration));
    }

    private List<Integer> convertGrayCodeToInts(List<List<Boolean>> grayCode) {
        return grayCode.stream().map(BinaryToDenary::convert).collect(Collectors.toList());
    }

    /*
Get next gray code sequence from previous by:
- reversing a copy of the previous codes
- adding 1 in front of each of them
- adding 0 in front of each of the previous codes
- collecting the reversed and previous codes into one list
 */
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

    /*
    creates gray codes up to (and including) iteration n, see https://en.wikipedia.org/wiki/Gray_code
     */
    private List<List<Boolean>> getGrayCode(final int nIteration) {
        // create base case n=1, [0, 1]
        List<List<Boolean>> grayCode = new ArrayList<>(Arrays.asList(toMutableList(List.of(false)), toMutableList(List.of(true))));
        if (nIteration <= 1) { return  grayCode; }

        for (int i = 0; i < (nIteration - 1); i++) {
            grayCode = generateNextGrayCode(grayCode);
        }

        return grayCode;

    }

    private <T> List<T> toMutableList (List<T> immutableList) {
        return new ArrayList<>(immutableList);
    }

}
