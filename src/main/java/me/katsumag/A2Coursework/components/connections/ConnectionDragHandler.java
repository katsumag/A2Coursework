package me.katsumag.A2Coursework.components.connections;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import me.katsumag.A2Coursework.util.ParentHelper;

public class ConnectionDragHandler implements EventHandler<MouseDragEvent> {

    /**
     * @param event the event which occurred
     */
    @Override
    public void handle(MouseDragEvent event) {

        if (!(event.getGestureSource() instanceof Circle startPoint)) { return; }
        if (!(event.getTarget() instanceof Circle endPoint)) { return; }

        if (startPoint == endPoint) { return; }

        Line line = new Line(startPoint.getCenterX(), startPoint.getCenterY(), endPoint.getCenterX(), endPoint.getCenterY());
        new ParentHelper().addChildTo(startPoint.getParent(), line);

        event.consume();

    }
}
