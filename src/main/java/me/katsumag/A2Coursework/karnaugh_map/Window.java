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
        for (int height = 0; height < this.windowY; height++) {
            List<Boolean> row = new ArrayList<>();
            for (int width = 0; width < this.windowX; width++) {
                row.add(this.map.get(height + this.currentY).get(width + this.currentX));
            }
            window.add(row);
        }
        return window;
    }

}
