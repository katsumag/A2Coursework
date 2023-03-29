package me.katsumag.A2Coursework.components.connections;

/**
  * Holds a state which represents whether a component's connection points are shown.
  * This is done to avoid edge cases involving mouse event spam if the cursor is on the edge of a component
 */
public class ConnectionState {

    private boolean canHide = false;
    private boolean canShow = ! canHide;

    public boolean canHide() {
        return canHide;
    }

    public boolean canShow() {
        return canShow;
    }

    public void flipState() {
        canShow = canHide;
        canHide = ! canHide;
    }

}
