package me.katsumag.A2Coursework.event_handlers;

import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import org.girod.javafx.svgimage.SVGImage;

public class GateStartDragHandler implements EventHandler<MouseEvent> {

    /**
     * Listens for the beginning of a drag event, then fires a {@link javafx.scene.input.DragEvent}
     */


    // start the drag event, works as intended
    @Override
    public void handle(MouseEvent event) {
        Object source = event.getSource();

        if (!(source instanceof SVGImage clicked)) return;

        // start drag and drop gesture
        Dragboard dragboard = clicked.startDragAndDrop(TransferMode.ANY);

        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(clicked.toString());
        dragboard.setContent(clipboardContent);

        dragboard.setDragView(clicked.toImage());

        event.consume();
    }

}
