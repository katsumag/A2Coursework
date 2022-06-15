package me.katsumag.A2Coursework;

import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import org.girod.javafx.svgimage.SVGImage;

public class GateStartDragHandler implements EventHandler<MouseEvent> {

    /*
     * Listens for the beginning of a drag event, then fires a {@link javafx.scene.input.DragEvent}
     * For some reason it seems JavaFX relies on you to fire a drag event in this way
     * https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm
     */

    @Override
    public void handle(MouseEvent event) {
        Object source = event.getSource();

        if (! (source instanceof SVGImage)) return;

        SVGImage clicked = ((SVGImage) source);

        Dragboard dragboard = clicked.startDragAndDrop(TransferMode.ANY);
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(clicked.toString());
        dragboard.setContent(clipboardContent);

        event.consume();
    }

}
