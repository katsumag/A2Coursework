package me.katsumag.A2Coursework.util;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;

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
    private ObservableList<Node> getChildrenOf(Parent parent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getChildren = parent.getClass().getDeclaredMethod("getChildren");
        getChildren.setAccessible(true);
        return (ObservableList<Node>) getChildren.invoke(parent);
    }

    public void addChildTo(Parent parent, Node child) {
        try {
            getChildrenOf(parent).add(child);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeChildFrom(Parent parent, Node child) {
        try {
            getChildrenOf(parent).remove(child);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
