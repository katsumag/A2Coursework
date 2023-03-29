package me.katsumag.A2Coursework.karnaugh_map;

import me.katsumag.A2Coursework.truth_table.DenaryToBinary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Holds a section of the Karnaugh Map
 */
public class Window {

    private final KarnaughMap map;
    private List<List<KarnaughMapEntry>> window;

    private final int windowX, windowY;
    private int currentX, currentY;

    /** Creates a new Window.
     * @param x Width of the window
     * @param y Height of the window
     * @param map The map to capture values from
    */
    public Window(int x, int y, KarnaughMap map) {
        this(x, y, 0, 0, map);
    }

    public Window(int windowX, int windowY, int currentX, int currentY, KarnaughMap map) {
        this.map = map;
        this.windowX = windowX;
        this.windowY = windowY;
        this.currentX = currentX;
        this.currentY = currentY;
        this.window = capture();
    }

    public int getWindowX() { return this.windowX; }

    public int getWindowY() { return this.windowY; }

    public int getCurrentX() { return this.currentX; }

    public int getCurrentY() { return this.currentY; }

    /** Shift the current x right by one, wrapping around if needed */
    public void shiftRight() {
        this.currentX += 1;
        if (this.currentX >= map.getMapSize()) { this.currentX = 0; }
        this.window = capture();
    }

    /** Shift the current Y up by one, wrapping from the bottom if needed */
    public void shiftUp() {
        this.currentY += 1;
        if (this.currentY >= map.getMapSize()) { this.currentY = 0; }
        this.window = capture();
    }

    public void resetX() { this.currentX = 0; }

    public void resetY() { this.currentY = 0; }

    public List<List<KarnaughMapEntry>> getWindow() { return this.window; }

    /** Using currentX/Y, captures values inside the window from the map */
    private List<List<KarnaughMapEntry>> capture() {
        List<List<KarnaughMapEntry>> window = new ArrayList<>();

        // loop through y values of window size
        for (int height = 0; height < this.windowY; height++) {
            // calc current row based on window position + current shift
            int yIndex = height + this.currentY;
            // handle overflows. Not amazing, but it *works*
            if (yIndex >= this.map.getInternalState().size()) { yIndex = yIndex % this.map.getInternalState().size(); }

            List<KarnaughMapEntry> row = new ArrayList<>();
            // loop through x values of window size
            for (int width = 0; width < this.windowX; width++) {
                // calc current column based on window position + current shift
                int xIndex = width + this.currentX;
                // handle overflows. Not amazing, but it *works*
                if (xIndex >= this.map.getInternalState().get(0).size()) { xIndex = xIndex % this.map.getInternalState().get(0).size(); }
                // copy element from map to window
                row.add(this.map.getInternalState().get(yIndex).get(xIndex));
            }
            window.add(row);
        }
        return window;
    }

    public boolean isValid() {
        // check there is at least previously unchecked entry
        long unCheckedEntries = this.window.stream().flatMap(row -> row.stream().filter(entry -> !entry.getChecked())).count();
        if (unCheckedEntries < 1) { return false; }

        // check that every entry is a 1
        boolean allOnes = this.window.stream().allMatch(row -> row.stream().allMatch(KarnaughMapEntry::getState));
        if (allOnes) {
            // mark every window entry as checked
            this.window.forEach(row -> row.forEach(KarnaughMapEntry::markChecked));
            return true;
        }
        return false;
    }

    public Window copy() {
        return new Window(this.windowX, this.windowY, this.currentX, this.currentY, this.map);
    }

    public String getExpression() {

        // get gray code
        int grayCodeIteration = (int) (Math.sqrt(this.map.getMapSize()) / 2);
        List<Integer> grayCode = new GrayCode().get(grayCodeIteration);

        List<List<Boolean>> symbolStates = new ArrayList<>();

        // add window headers into list
        for (int y = 0; y < this.window.size(); y++) {
            for (int x = 0; x < this.window.get(y).size(); x++) {
                List<Boolean> yHeader = pad(DenaryToBinary.convert(grayCode.get(this.currentY + y)), grayCodeIteration);
                List<Boolean> xHeader = pad(DenaryToBinary.convert(grayCode.get(this.currentX + x)), grayCodeIteration);
                System.out.println("xHeader = " + xHeader);
                System.out.println("yHeader = " + yHeader);
                xHeader.addAll(yHeader);
                symbolStates.add(xHeader);
            }
        }

        symbolStates = convertToColumns(symbolStates);

        System.out.println("symbolStates = " + symbolStates);

        List<String> symbolStringList = new ArrayList<>();

        for (int i = 0; i < symbolStates.size(); i++) {
            // "local variables used in lambda expressions must be final or effectively final"
            int finalI = i;
            List<Boolean> symbolColumn = symbolStates.get(i);
            System.out.println("symbolColumn = " + symbolColumn);
            char symbol = (char) (65 + i);

            if (symbolColumn.stream().allMatch(aBoolean -> aBoolean == symbolColumn.get(0))) {
                if (symbolColumn.get(0)) { symbolStringList.add("" + symbol); } else { symbolStringList.add("NOT " + symbol); }
            }

        }

        return "(" + String.join(" AND ", symbolStringList) + ")";

    }

    private List<Boolean> pad(List<Boolean> original, int desiredLength) {
        for (int i = 0; i < desiredLength - original.size(); i++) {
            original.add(0, false);
        }
        return original;
    }

    private List<List<Boolean>> convertToColumns(List<List<Boolean>> original) {
        List<List<Boolean>> columns = prefillRows(new ArrayList<>(), original.get(0).size());
        for (int x = 0; x < original.size(); x++) {
            for (int y = 0; y < original.get(x).size(); y++) {
                columns.get(y).add(original.get(x).get(y));
            }
        }
        return columns;
    }

    private List<List<Boolean>> prefillRows(List<List<Boolean>> original, int desiredSize) {
        for (int i = 0; i < desiredSize; i++) {
            original.add(new ArrayList<>());
        }
        return original;
    }

}
