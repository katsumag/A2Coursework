package me.katsumag.A2Coursework.karnaugh_map;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Windows {

    private KarnaughMap map;

    public Windows(final KarnaughMap map) {
        this.map = map;
    }

    public List<Pair<Integer, Integer>> getWindowSizes() {
        int mapSize = this.map.getMapSize();
        List<Pair<Integer, Integer>> dimensions = new ArrayList<>();
        while (mapSize >= 1) {
            dimensions.addAll(_getWindowSize(mapSize));
            mapSize /= 2;
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

}
