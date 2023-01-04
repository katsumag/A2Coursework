package me.katsumag.A2Coursework.karnaugh_map;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

}
