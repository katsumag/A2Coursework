package me.katsumag.A2Coursework.truth_table;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import me.katsumag.A2Coursework.components.CircuitComponentType;
import me.katsumag.A2Coursework.components.ComponentStore;
import me.katsumag.A2Coursework.components.connections.Connection;
import org.girod.javafx.svgimage.SVGImage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Generates a binary tree from the placed components
 */
public class TreeGenerator {

    public me.katsumag.A2Coursework.truth_table.Node getTreeFromPlacedComponents(MouseEvent event) {
        // get the truth table button
        Node button = (Node) event.getSource();

        // getParent = VBox
        // getParent = BorderPane
        // getCenter should be Pane
        // work backwards from there to get the right-hand panel
        Pane pane = ((Pane) ((BorderPane) button.getParent().getParent()).getCenter());

        // get the lamp component that signals the end of the circuit
        Optional<Node> lamp = pane.getChildren().stream()
                .filter(child -> child instanceof SVGImage)
                .filter(image -> image.getProperties().get("CircuitComponentType") == CircuitComponentType.LAMP.getName())
                .findFirst();

        // construct a tree by recursing from the lamp node, which acts as the root.
        // returns null if there is no lamp and so no tree can be made
        return lamp.map(node -> constructTree(new me.katsumag.A2Coursework.truth_table.Node(
                new ComponentStore().getComponentByUUID(
                        (UUID) node.getProperties().get("ComponentUUID")
                )
        ))).orElse(null);

    }

    // construct a tree from the root
    private me.katsumag.A2Coursework.truth_table.Node constructTree(me.katsumag.A2Coursework.truth_table.Node root) {
        ComponentStore componentStore = new ComponentStore();

        // get list of connected inputs
        List<Connection> inputs = root.getCircuitComponent().getConnections().getInputs()
                .stream().filter(connection -> connection != null && connection.getConnectedLine() != null).toList();


        // set children - don't think it matters which side they end up on, the gates won't care anyway
        switch (inputs.size()) {

            case 1 -> {
                root.setLeftChild(new me.katsumag.A2Coursework.truth_table.Node(
                        componentStore.getComponentByImage(inputs.get(0).getConnectedPoint().getParentImage())
                ));
            }

            case 2 -> {
                root.setLeftChild(new me.katsumag.A2Coursework.truth_table.Node(
                        componentStore.getComponentByImage(inputs.get(0).getConnectedPoint().getParentImage())
                ));

                root.setRightChild(new me.katsumag.A2Coursework.truth_table.Node(
                        componentStore.getComponentByImage(inputs.get(1).getConnectedPoint().getParentImage())
                ));
            }

            default -> {
            }
        }

        // recurse
        if (root.getLeftChild() != null) { constructTree(root.getLeftChild()); }
        if (root.getRightChild() != null) { constructTree(root.getRightChild()); }

        return root;
    }

}
