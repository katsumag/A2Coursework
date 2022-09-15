package me.katsumag.A2Coursework.util;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ParentHelper {

    @SuppressWarnings("unchecked cast")

    /*
     * Gets the list of a Parent's children. This is supposed to be impossible with JavaFX
     * However, I don't see a way around doing this.
     * So, I've used reflection to access the protected function getChildren
     * Keeping it private to attempt to follow JavaFX in not directly publicly exposing it.
     * Returns null only when the Parent doesn't implement getChildren, which should never happen
     * Only in erroneous cases will this return null, which seems fine to me.
     */
    private ObservableList<Node> getChildrenOf(@NotNull Parent parent)  {
        try {
            Method getChildren = parent.getClass().getDeclaredMethod("getChildren");
            getChildren.setAccessible(true);
            return (ObservableList<Node>) getChildren.invoke(parent);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void addChildTo(@NotNull Parent parent, Node child) {
        ObservableList<Node> children = getChildrenOf(parent);
        System.out.println("Child to add = " + child);
        //System.out.println("children = " + children);
        children.add(child);
        //System.out.println("Children after addition = " + children);
    }

    public boolean removeChildFrom(@NotNull Parent parent, Node child) {
        ObservableList<Node> children = getChildrenOf(parent);
        System.out.println("Child to remove = " + child);
        //System.out.println("children = " + children);
        return children.remove(child);
        //System.out.println("Children after removing = " + children);
    }


}
