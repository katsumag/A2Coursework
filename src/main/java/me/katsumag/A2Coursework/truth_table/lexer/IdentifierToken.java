package me.katsumag.A2Coursework.truth_table.lexer;

import me.katsumag.A2Coursework.components.CircuitComponent;
import me.katsumag.A2Coursework.components.Switch;

public class IdentifierToken implements Token {

    private final boolean state;

    public IdentifierToken(CircuitComponent component) {
        this.state = ((Switch) component).getState();
    }

    public boolean getState() { return this.state; }

}