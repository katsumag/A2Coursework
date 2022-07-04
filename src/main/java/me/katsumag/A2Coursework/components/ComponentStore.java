package me.katsumag.A2Coursework.components;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComponentStore {

    private static final List<CircuitComponent> components = new ArrayList<>();

    public void registerComponent(CircuitComponent component) { components.add(component); }

    /**
     *
     * @param uuid
     * @return the {@link CircuitComponent} with that uuid
     * @throws java.util.NoSuchElementException if an element with the provided UUID doesn't exist
     */
    public @NotNull CircuitComponent getComponentByUUID(UUID uuid) {
        return components.stream().filter(component -> component.getUUID() == uuid).findFirst().get();
    }

}
