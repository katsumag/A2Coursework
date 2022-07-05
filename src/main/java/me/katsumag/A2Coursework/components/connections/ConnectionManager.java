package me.katsumag.A2Coursework.components.connections;

import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
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
    private List<Connection> inputs = new ArrayList<>();
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

    public void addInputs(Connection... inputs) {
        this.inputs.addAll(Arrays.asList(inputs));
    }

    public void setOutput(Connection output) {
        this.output = output;
    }

    /**
     * Draws the {@link Connection}s {@link javafx.scene.shape.Circle} onto the {@link SVGImage}s parent layout
     * @param image to modify
     */
    public void drawConnectionPoints(SVGImage image) {
        Bounds bounds = image.getLayoutBounds();

        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#layoutXProperty

        Point2D topLeft = new Point2D(image.getLayoutX() - bounds.getMinX(), image.getLayoutY() - bounds.getMinY());
        Point2D bottomLeft = new Point2D(image.getLayoutX() - bounds.getMinX(), image.getLayoutY() + bounds.getMaxY());
        Point2D midRight = new Point2D(image.getLayoutX() + bounds.getWidth(), image.getLayoutY() + (0.5 * bounds.getHeight()));

        this.addInputs(new Connection(topLeft.getX(), topLeft.getY()));
        this.addInputs(new Connection(bottomLeft.getX(), bottomLeft.getY()));
        this.output = new Connection(midRight.getX(), midRight.getY());

        this.inputs.forEach(circle -> {
            try {
                new ParentHelper().getChildrenOf(image.getParent()).add(circle.getCircle());
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        try {
            new ParentHelper().getChildrenOf(image.getParent()).add(this.output.getCircle());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

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
