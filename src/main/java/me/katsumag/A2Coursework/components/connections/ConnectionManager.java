package me.katsumag.A2Coursework.components.connections;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
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

    public ConnectionManager(ConnectionNumber inputsNum, ConnectionNumber outputsNum, List<Connection> inputs, Connection output) {
        this.inputConnectionNumber = inputsNum;
        this.outputConnectionNumber = outputsNum;
        this.inputs = inputs;
        this.output = output;
    }

    public ConnectionManager(ConnectionNumber inputsNum, ConnectionNumber outputsNum) {
        this.inputConnectionNumber = inputsNum;
        this.outputConnectionNumber = outputsNum;
    }

    /**
     *
     * @return List of {@link Connection}s coming into the {@link me.katsumag.A2Coursework.components.CircuitComponent}
     * Can be null if there are no inputs, or inputs have not been initialised
     */
    public @Nullable List<Connection> getInputs() {
        return inputs;
    }

    /**
     *
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

    public void drawConnectionPoints(SVGImage image) {
        Bounds bounds = image.getLayoutBounds();
        Point2D topLeft = new Point2D(bounds.getMinX(), bounds.getMinY());
        Point2D topRight = new Point2D(bounds.getMinX() + bounds.getWidth(), bounds.getMinY());
        this.addInputs(new Connection(topLeft.getX(), topLeft.getY()));
        this.addInputs(new Connection(topRight.getX(), topRight.getY()));
        this.inputs.forEach(circle -> {
            try {
                new ParentHelper().getChildrenOf(image.getParent()).add(circle.getCircle());
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
