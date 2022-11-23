package me.katsumag.A2Coursework.karnaugh_map;

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
