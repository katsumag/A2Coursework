package me.katsumag.A2Coursework.components.connections;

import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import me.katsumag.A2Coursework.util.BoundsHelper;
import me.katsumag.A2Coursework.util.ParentHelper;
import org.girod.javafx.svgimage.SVGImage;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConnectionManager {

    private final ConnectionNumber inputConnectionNumber;
    private final ConnectionNumber outputConnectionNumber;
    private final List<Connection> inputs = new ArrayList<>();
    private Connection output;


    public ConnectionManager(ConnectionNumber inputsNum, ConnectionNumber outputsNum) {
        this.inputConnectionNumber = inputsNum;
        this.outputConnectionNumber = outputsNum;
    }

    /**
     * @return List of {@link Connection}s coming into the {@link me.katsumag.A2Coursework.components.CircuitComponent}
     * Can be null if there are no inputs, or inputs have not been initialised
     */
    public @Nullable List<Connection> getInputs() {
        return inputs;
    }

    /**
     * @return The {@link Connection} coming out of the {@link me.katsumag.A2Coursework.components.CircuitComponent}
     * Can be null if the output isn't initialised
     */
    public @Nullable Connection getOutput() {
        return output;
    }

    private void addInputs(Connection... inputs) {
        this.inputs.addAll(Arrays.asList(inputs));
    }

    private void setOutput(Connection output) {
        this.output = output;
    }

    /**
     * Draws the {@link Connection}s {@link javafx.scene.shape.Circle} onto the {@link SVGImage}s parent layout
     * @param image to modify
     */
    public void drawConnectionPoints(SVGImage image) {

        // handle drawing input connection points
        switch (this.inputConnectionNumber) {
            case ONE -> drawInputConnectionPoint(image);
            case TWO -> drawInputConnectionPoints(image);
            // Do nothing
            default -> {  }
        }

        // handle drawing output connection points
        if (this.outputConnectionNumber == ConnectionNumber.ONE) {
            drawOutputConnectionPoint(image);
        }

        // add each Connection's circle to the SVGImage's Parent so that it's displayed on screen.

        try {
            ObservableList<Node> children = new ParentHelper().getChildrenOf(image.getParent());

            // add input circles
            this.inputs.forEach(connection -> children.add(connection.getCircle()));
            // add output circle (if it exists)
            if (this.output != null) { children.add(this.output.getCircle()); }

        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void drawInputConnectionPoint(SVGImage image) {
        Point2D connectionPoint = new BoundsHelper(image).getMiddleLeft();
        this.addInputs(new Connection(connectionPoint.getX(), connectionPoint.getY()));
    }

    private void drawInputConnectionPoints(SVGImage image) {
        BoundsHelper boundsHelper = new BoundsHelper(image);
        Point2D topConnectionPoint = boundsHelper.getTopLeft();
        Point2D bottomConnectionPoint = boundsHelper.getBottomLeft();
        this.addInputs(
                new Connection(topConnectionPoint.getX(), topConnectionPoint.getY()),
                new Connection(bottomConnectionPoint.getX(), bottomConnectionPoint.getY())
        );
    }

    private void drawOutputConnectionPoint(SVGImage image) {
        Point2D connectionPoint = new BoundsHelper(image).getMiddleRight();
        this.setOutput(new Connection(connectionPoint.getX(), connectionPoint.getY()));
    }

    /**
     * Removes the {@link Connection}s {@link javafx.scene.shape.Circle} from the parent layout
     * Preserves the underlying {@link Connection}s
     * @param image the image to modify
     */
    public void hideConnectionPoints(SVGImage image) {
        try {
            ObservableList<Node> children = new ParentHelper().getChildrenOf(image.getParent());
            this.inputs.forEach(connection -> children.remove(connection.getCircle()));
            children.remove(this.output.getCircle());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the {@link Connection}s {@link javafx.scene.shape.Circle} from the parent layout
     * Clears any connections. This is done because this is designed to be used when the
     * {@link me.katsumag.A2Coursework.components.CircuitComponent} has been moved on the screen
     * And so its previous connection points are now invalid
     * @param svgImage the image to modify
     */
    public void removeConnectionPoints(SVGImage svgImage) {
        hideConnectionPoints(svgImage);
        resetConnectionPoints();
    }

    private void resetConnectionPoints() {
        this.inputs.clear();
        this.output = null;
    }

}
