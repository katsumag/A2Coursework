package me.katsumag.A2Coursework.karnaugh_map;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles identifying windows from a Karnaugh map
 * Tries every combination of window possible
 */
public class Windows {

    private KarnaughMap map;

    public Windows(final KarnaughMap map) {
        this.map = map;
    }

    // gets all possible window dimensions that could be made from the karnaugh maps
    public List<Pair<Integer, Integer>> getWindowSizes() {
        int mapSize = this.map.getMapSize();
        List<Pair<Integer, Integer>> dimensions = new ArrayList<>();
        while (mapSize >= 1) {
            dimensions.addAll(_getWindowSize(mapSize));
            mapSize /= 2;
        }
        return dimensions;
    }

    // Stores all possible window dimensions for each possible map size
    // Far easier than trying to dynamically generate these, even if they are just factors
    private List<Pair<Integer, Integer>> _getWindowSize(int mapSize) {
        return switch (mapSize) {
            case 16 -> List.of(new Pair<>(4, 4));
            case 8 -> List.of(new Pair<>(2, 4), new Pair<>(4, 2));
            case 4 -> List.of(new Pair<>(2, 2), new Pair<>(1, 4), new Pair<>(4, 1));
            case 2 -> List.of(new Pair<>(1, 2), new Pair<>(2, 1));
            case 1 -> List.of(new Pair<>(1, 1));
            default -> List.of();
        };
    }

    // Create windows with all possible sizes in their default (unshifted) positions
    public List<Window> getDefaultPositionWindows() {
        return getWindowSizes().stream().map(size -> new Window(size.getKey(), size.getValue(), this.map)).toList();
    }

    // Gets all valid windows by shifting the default windows around into all possible positions
    // Then checks that each window only contains 1s, and that at least one 1 hasn't been used before
    public List<Window> getValidWindows() {
        List<Window> validWindows = new ArrayList<>();
        getDefaultPositionWindows().forEach(window -> {
            while (window.getCurrentY() < this.map.getMapYSize()) {
                while (window.getCurrentX() < this.map.getMapXSize()) {
                    if (window.isValid()) {
                        // add a copy of the window, since the object is reused
                        validWindows.add(window.copy());
                    }
                    window.shiftRight();
                }
                window.shiftUp();
                window.resetX();
            }
        });
        return validWindows;
    }

}
