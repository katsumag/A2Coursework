package me.katsumag.A2Coursework.truth_table.lexer;

import me.katsumag.A2Coursework.components.CircuitComponent;
import me.katsumag.A2Coursework.components.Switch;

/**
 * Represents all switches
 */
public class IdentifierToken implements Token {

    private final boolean state;

    public IdentifierToken(CircuitComponent component) {
        this.state = ((Switch) component).getState();
    }

    /**
     * DESIGNED FOR TESTING PURPOSES
     * Nothing will happen if used normally, though
     * @param state - the desired state
     */
    public IdentifierToken(boolean state) { this.state = state; }

    public boolean getState() { return this.state; }

}
