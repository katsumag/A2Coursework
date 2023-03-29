package me.katsumag.A2Coursework.karnaugh_map;

/**
 * Represents one cell of a Karnaugh Map.
 * Exists so that I can easily check if a cell has already become part of a window in the final stages
 */
public class KarnaughMapEntry {

    private final Boolean state;
    private Boolean checked = false;

    public KarnaughMapEntry(boolean state) {
        this.state = state;
    }

    public boolean getState() { return state; }

    public void markChecked() { this.checked = true; }

    public boolean getChecked() { return this.checked; }

}
