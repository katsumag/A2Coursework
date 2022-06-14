package me.katsumag.A2Coursework;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;

public class GateStopDragHandler implements EventHandler<DragEvent> {

    @Override
    public void handle(DragEvent event) {
        System.out.println("Stop drag");
    }

}
