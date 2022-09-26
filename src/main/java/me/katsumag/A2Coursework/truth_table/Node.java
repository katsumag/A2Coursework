package me.katsumag.A2Coursework.truth_table;

import me.katsumag.A2Coursework.components.CircuitComponent;

public class Node {

    private Node leftChild, rightChild;
    private final CircuitComponent circuitComponent;

    public Node(CircuitComponent circuitComponent) { this.circuitComponent = circuitComponent; }

    public CircuitComponent getCircuitComponent() { return this.circuitComponent; }

    /*
    /**
     * @param root {@link Node} to set as the Node's root node

    public void setRoot(Node root) { this.root = root; }

    /**
     * @return the Node's root node

    public Node getRoot() { return this.root; }
     */


    /**
     * @param leftChild {@link Node} to set as the Node's left child
     */
    public void setLeftChild(Node leftChild) { this.leftChild = leftChild; }

    /**
     * @return the Node's left child
     */
    public Node getLeftChild() { return this.leftChild; }

    /**
     * @param rightChild {@link Node} to set as the Node's right child
     */
    public void setRightChild(Node rightChild) { this.rightChild = rightChild; }

    /**
     * @return the Node's right child
     */
    public Node getRightChild() { return this.rightChild; }

}
