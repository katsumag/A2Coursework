package me.katsumag.A2Coursework.util;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ParentHelper {

    @SuppressWarnings("unchecked cast")
    public ObservableList<Node> getChildrenOf(Parent parent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getChildren = parent.getClass().getDeclaredMethod("getChildren");
        getChildren.setAccessible(true);
        return (ObservableList<Node>) getChildren.invoke(parent);
    }

}
