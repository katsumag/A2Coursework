package me.katsumag.A2Coursework.components;

import me.katsumag.A2Coursework.components.connections.Connection;
import org.girod.javafx.svgimage.SVGImage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
  * Holds {@link CircuitComponent}s and {@link Connection}s so that they can be retrieved by their {@link UUID}s
 */
public class ComponentStore {

    private static final List<CircuitComponent> components = new ArrayList<>();
    private static final List<Connection> connections = new ArrayList<>();

    public void registerComponent(CircuitComponent component) { components.add(component); }

    public void registerConnection(Connection connection) { connections.add(connection); }

    /**
     * @param uuid The UUID to search for
     * @return the {@link CircuitComponent} with that uuid
     * @throws java.util.NoSuchElementException if an element with the provided UUID doesn't exist
     */
    public @NotNull CircuitComponent getComponentByUUID(@NotNull UUID uuid) {
        return components.stream().filter(component -> component.getUUID() == uuid).findFirst().get();
    }

    /**
     * @param image The {@link SVGImage} to get the UUID from
     * @return The {@link CircuitComponent} instance corresponding to the provided {@link SVGImage}
     */
    public @NotNull CircuitComponent getComponentByImage(@NotNull SVGImage image) {
        return getComponentByUUID((UUID) image.getProperties().get("ComponentUUID"));
    }

    /**
     * @param uuid The UUID to search for
     * @return The {@link Connection} with the corresponding UUID
     */
    public @NotNull Connection getConnectionByUUID(@NotNull UUID uuid) {
        return connections.stream().filter(connection -> connection.getUUID() == uuid).findFirst().get();
    }

}
