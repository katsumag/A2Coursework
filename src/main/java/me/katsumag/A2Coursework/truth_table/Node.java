package me.katsumag.A2Coursework.truth_table;

import me.katsumag.A2Coursework.components.CircuitComponent;

public class Node {

    private Node leftChild, rightChild;
    private final CircuitComponent circuitComponent;

    public Node(CircuitComponent circuitComponent) { this.circuitComponent = circuitComponent; }

    // Customised toString, which produces the tree as a flat string
    @Override
    public String toString() {
        String leftChildString = (this.leftChild == null) ? "null" : this.leftChild.toString();
        String rightChildString = (this.rightChild == null) ? "null" : this.rightChild.toString();

        return "[" + this.circuitComponent + ": [Left=" + leftChildString + "] [Right=" + rightChildString + "]]";
    }

    /**
     * @return the {@link Node}'s data
     */
    public CircuitComponent getCircuitComponent() { return this.circuitComponent; }

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

    /**
     * <a href="https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/">Source</a>
     */
    static void print2DUtil(Node root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += 10;

        // Process right child first
        print2DUtil(root.getRightChild(), space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = 10; i < space; i++)
            System.out.print(" ");
        System.out.print(root.circuitComponent + "\n");

        // Process left child
        print2DUtil(root.getLeftChild(), space);
    }

}
