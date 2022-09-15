package me.katsumag.A2Coursework.components.connections;

import javafx.geometry.Point2D;
import me.katsumag.A2Coursework.components.CircuitComponentType;
import me.katsumag.A2Coursework.util.BoundsHelper;
import me.katsumag.A2Coursework.util.ParentHelper;
import org.girod.javafx.svgimage.SVGImage;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ConnectionManager {

    private final ConnectionNumber inputConnectionNumber;
    private final ConnectionNumber outputConnectionNumber;
    private final List<Connection> inputs = new ArrayList<>();
    private Connection output;

    private final ConnectionState state = new ConnectionState();


    public ConnectionManager(ConnectionNumber inputsNum, ConnectionNumber outputsNum) {
        this.inputConnectionNumber = inputsNum;
        this.outputConnectionNumber = outputsNum;
    }

    /**
     * @return a clone of inputs, with the output connected added.
     */
    public List<Connection> getAllConnectionPoints() {
        List<Connection> allPoints =  new ArrayList<>(this.inputs);
        allPoints.add(this.output);
        return allPoints;
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

        if (this.state.canHide()) { return; }

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
        showConnectionPoints(image);
    }

    private void drawInputConnectionPoint(SVGImage image) {
        Point2D connectionPoint = new BoundsHelper(image).getMiddleLeft();
        this.addInputs(new Connection(connectionPoint.getX(), connectionPoint.getY(), image));
    }

    private void drawInputConnectionPoints(SVGImage image) {
        BoundsHelper boundsHelper = new BoundsHelper(image);
        Point2D topConnectionPoint = boundsHelper.getTopLeft();
        Point2D bottomConnectionPoint = boundsHelper.getBottomLeft();
        this.addInputs(
                new Connection(topConnectionPoint.getX(), topConnectionPoint.getY(), image),
                new Connection(bottomConnectionPoint.getX(), bottomConnectionPoint.getY(), image)
        );
    }

    private void drawOutputConnectionPoint(SVGImage image) {

        Point2D connectionPoint;
        BoundsHelper boundsHelper = new BoundsHelper(image);

        // account for the difference between heights of images. Shouldn't be an issue, but is.
        String componentType = (String) image.getProperties().get("CircuitComponentType");
        if (Objects.equals(componentType, CircuitComponentType.NOT.getName()) || Objects.equals(componentType, CircuitComponentType.SWITCH.getName())) {
            connectionPoint = boundsHelper.getHigherMiddleRight();
        } else {
            connectionPoint = boundsHelper.getMiddleRight();
        }

        this.setOutput(new Connection(connectionPoint.getX(), connectionPoint.getY(), image));
    }

    /**
     * Removes the {@link Connection}s {@link javafx.scene.shape.Circle} from the parent layout
     * Preserves the underlying {@link Connection}s
     * @param image the image to modify
     */
    public void hideConnectionPoints(SVGImage image) {
        if (this.state.canShow()) { return; }
        ParentHelper parentHelper = new ParentHelper();
        this.inputs.forEach(connection -> parentHelper.removeChildFrom(image.getParent(), connection.getCircle()));
        this.state.flipState();
    }

    public void showConnectionPoints(SVGImage image) {
        if (this.state.canHide()) { return; }
        ParentHelper parentHelper = new ParentHelper();
        this.inputs.forEach(connection -> parentHelper.addChildTo(image.getParent(), connection.getCircle()));
        parentHelper.addChildTo(image.getParent(), this.output.getCircle());
        this.state.flipState();
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

    public void refreshConnectionPoints(SVGImage svgImage) {

        BoundsHelper boundsHelper = new BoundsHelper(svgImage);

        switch (this.inputConnectionNumber) {
            case ONE -> {
                Point2D newLocation = boundsHelper.getMiddleLeft();
                this.inputs.get(0).getCircle().relocate(newLocation.getX(), newLocation.getY());
            }
            case TWO -> {
                // inputs list should maintain insertion order, so top is 0 and bottom is 1
                Point2D newTopLocation = boundsHelper.getTopLeft();
                Point2D newBottomLocation = boundsHelper.getBottomLeft();
                this.inputs.get(0).getCircle().relocate(newTopLocation.getX(), newTopLocation.getY());
                this.inputs.get(1).getCircle().relocate(newBottomLocation.getX(), newBottomLocation.getY());
            }
            default -> {}
        }

        Point2D newOutputLocation = boundsHelper.getMiddleRight();
        this.output.getCircle().relocate(newOutputLocation.getX(), newOutputLocation.getY());

    }

}
