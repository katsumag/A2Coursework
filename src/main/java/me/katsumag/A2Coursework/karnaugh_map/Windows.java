package me.katsumag.A2Coursework.karnaugh_map;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Windows {

    private List<List<Boolean>> map;
    private int mapSize;

    public Windows(final List<List<Boolean>> map) {
        this.map = map;
        this.mapSize = map.size() * map.get(0).size();
    }

    public List<Pair<Integer, Integer>> getWindowSizes() {
        List<Pair<Integer, Integer>> dimensions = new ArrayList<>();
        while (this.mapSize >= 1) {
            dimensions.addAll(_getWindowSize(this.mapSize));
            this.mapSize /= 2;
        }
        return dimensions;
    }

    private List<Pair<Integer, Integer>> _getWindowSize(int mapSize) {
        return switch (mapSize) {
            case 16 -> List.of(new Pair<>(4, 4));
            case 8 -> List.of(new Pair<>(2, 4));
            case 4 -> List.of(new Pair<>(2, 2), new Pair<>(1, 4));
            case 2 -> List.of(new Pair<>(1, 2));
            case 1 -> List.of(new Pair<>(1, 1));
            default -> List.of();
        };
    }

    public List<Window> getDefaultPositionWindows() {
        return getWindowSizes().stream().map(size -> new Window(size.getKey(), size.getValue(), this.map)).toList();
    }

    public boolean windowIsValid(Window window) {

    }

}
