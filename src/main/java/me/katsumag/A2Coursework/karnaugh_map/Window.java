package me.katsumag.A2Coursework.karnaugh_map;

import java.util.ArrayList;
import java.util.List;

public class Window {

    private final List<List<Boolean>> map;
    private List<List<Boolean>> window;

    private final int windowX, windowY;
    private int currentX, currentY;

    /** Creates a new Window.
     * @param x Width of the window
     * @param y Height of the window
     * @param map The map to capture values from
    */
    public Window(int x, int y, List<List<Boolean>> map) {
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
        if (this.currentX >= map.size()) { this.currentX = 0; }
        this.window = capture();
    }

    /** Shift the current Y up by one, wrapping from the bottom if needed */
    public void shiftUp() {
        this.currentY += 1;
        if (this.currentY >= map.size()) { this.currentY = 0; }
        this.window = capture();
    }

    public List<List<Boolean>> getWindow() { return this.window; }

    /** Using currentX/Y, captures values inside the window from the map */
    private List<List<Boolean>> capture() {
        List<List<Boolean>> window = new ArrayList<>();

        // loop through y values of window size
        for (int height = 0; height < this.windowY; height++) {
            // calc current row based on window position + current shift
            int yIndex = height + this.currentY;
            // handle overflows. Not amazing, but it *works*
            if (yIndex >= this.map.size()) { yIndex = yIndex % this.map.size(); }

            List<Boolean> row = new ArrayList<>();
            // loop through x values of window size
            for (int width = 0; width < this.windowX; width++) {
                // calc current column based on window position + current shift
                int xIndex = width + this.currentX;
                // handle overflows. Not amazing, but it *works*
                if (xIndex >= this.map.size()) { xIndex = xIndex % this.map.size(); }
                // copy element from map to window
                row.add(this.map.get(yIndex).get(xIndex));
            }
            window.add(row);
        }
        return window;
    }

    public boolean validate() {
        return this.window.stream().allMatch(row -> row.stream().allMatch(value -> value));
    }

}
