package me.katsumag.A2Coursework.util;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ParentHelper {

    @SuppressWarnings("unchecked cast")

    /*
     * Gets the list of a Parent's children. This is supposed to be impossible with JavaFX
     * However, I don't see a way around doing this.
     * So, I've used reflection to access the protected function getChildren
     * Returns null only when the Parent doesn't implement getChildren, which should never happen
     * Only in erroneous cases will this return null, which seems fine to me.
     */
    public ObservableList<Node> getChildrenOf(@NotNull Parent parent)  {
        try {
            Method getChildren = parent.getClass().getDeclaredMethod("getChildren");
            getChildren.setAccessible(true);
            return (ObservableList<Node>) getChildren.invoke(parent);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /*
     * Adds the child Node to the Parent's children.
     * I use this to display the child Node on the screen.
     */
    public void addChildTo(@NotNull Parent parent, Node child) {
        ObservableList<Node> children = getChildrenOf(parent);
        children.add(child);
    }

    /*
     * Removes the child Node from the Parent's children.
     * I use this to remove the child Node from the screen.
     */
    public boolean removeChildFrom(@NotNull Parent parent, Node child) {
        ObservableList<Node> children = getChildrenOf(parent);
        return children.remove(child);
    }


}
