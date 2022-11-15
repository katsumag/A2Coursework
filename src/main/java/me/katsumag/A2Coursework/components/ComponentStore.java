package me.katsumag.A2Coursework.components;

import me.katsumag.A2Coursework.components.connections.Connection;
import org.girod.javafx.svgimage.SVGImage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComponentStore {

    private static final List<CircuitComponent> components = new ArrayList<>();
    private static final List<Connection> connections = new ArrayList<>();

    public void registerComponent(CircuitComponent component) { components.add(component); }

    public void registerConnection(Connection connection) { connections.add(connection); }

    /**
     *
     * @param uuid
     * @return the {@link CircuitComponent} with that uuid
     * @throws java.util.NoSuchElementException if an element with the provided UUID doesn't exist
     */
    public @NotNull CircuitComponent getComponentByUUID(@NotNull UUID uuid) {
        return components.stream().filter(component -> component.getUUID() == uuid).findFirst().get();
    }

    public @NotNull CircuitComponent getComponentByImage(@NotNull SVGImage image) {
        return getComponentByUUID((UUID) image.getProperties().get("ComponentUUID"));
    }

    public @NotNull Connection getConnectionByUUID(@NotNull UUID uuid) {
        return connections.stream().filter(connection -> connection.getUUID() == uuid).findFirst().get();
    }

}
