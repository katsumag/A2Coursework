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
        this.map = map;
        this.windowX = x;
        this.windowY = y;
        this.currentX = 0;
        this.currentY = 0;
        this.window = capture();
    }

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

    public List<List<KarnaughMapEntry>> getWindow() { return this.window; }

    /** Using currentX/Y, captures values inside the window from the map */
    private List<List<KarnaughMapEntry>> capture() {
        List<List<KarnaughMapEntry>> window = new ArrayList<>();

        // loop through y values of window size
        for (int height = 0; height < this.windowY; height++) {
            // calc current row based on window position + current shift
            int yIndex = height + this.currentY;
            // handle overflows. Not amazing, but it *works*
            if (yIndex >= this.map.getMapSize()) { yIndex = yIndex % this.map.getMapSize(); }

            List<KarnaughMapEntry> row = new ArrayList<>();
            // loop through x values of window size
            for (int width = 0; width < this.windowX; width++) {
                // calc current column based on window position + current shift
                int xIndex = width + this.currentX;
                // handle overflows. Not amazing, but it *works*
                if (xIndex >= this.map.getMapSize()) { xIndex = xIndex % this.map.getMapSize(); }
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

}
