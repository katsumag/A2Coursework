package me.katsumag.A2Coursework.components.connections;

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
