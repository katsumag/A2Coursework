package me.katsumag.A2Coursework.components.connections;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class ConnectionClickHandler implements EventHandler<MouseEvent> {


    /**
     * @param event the event which occurred
     */
    @Override
    public void handle(MouseEvent event) {

        if (!(event.getSource() instanceof Circle startPoint)) { return; }

        //startPoint.startDragAndDrop(TransferMode.NONE);

        System.out.println("Connection Point Clicked");


        event.consume();
    }
}
