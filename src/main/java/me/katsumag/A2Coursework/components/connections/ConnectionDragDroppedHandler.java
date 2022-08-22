package me.katsumag.A2Coursework.components.connections;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import me.katsumag.A2Coursework.util.ParentHelper;

public class ConnectionDragDroppedHandler implements EventHandler<DragEvent> {

    /**
     * @param event the event which occurred
     */
    @Override
    public void handle(DragEvent event) {

        if (!(event.getGestureSource() instanceof Circle startPoint)) { return; }
        if (!(event.getGestureTarget() instanceof Circle endPoint)) { return; }

        if (startPoint == endPoint) { return; }

        Line line = new Line(startPoint.getLayoutX(), startPoint.getLayoutY(), endPoint.getLayoutX(), endPoint.getLayoutY());
        new ParentHelper().addChildTo(startPoint.getParent(), line);

        event.setDropCompleted(true);

        event.consume();

    }
}