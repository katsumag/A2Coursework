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
     * Keeping it private to attempt to follow JavaFX in not directly publicly exposing it.
     */
    private ObservableList<Node> getChildrenOf(@NotNull Parent parent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getChildren = parent.getClass().getDeclaredMethod("getChildren");
        getChildren.setAccessible(true);
        return (ObservableList<Node>) getChildren.invoke(parent);
    }

    public void addChildTo(@NotNull Parent parent, Node child) {
        try {
            ObservableList<Node> children = getChildrenOf(parent);
            System.out.println("Child to add = " + child);
            System.out.println("children = " + children);
            children.add(child);
            System.out.println("Children after addition = " + children);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeChildFrom(@NotNull Parent parent, Node child) {
        try {
            ObservableList<Node> children = getChildrenOf(parent);
            System.out.println("Child to remove = " + child);
            System.out.println("children = " + children);
            children.remove(child);
            System.out.println("Children after removing = " + children);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
