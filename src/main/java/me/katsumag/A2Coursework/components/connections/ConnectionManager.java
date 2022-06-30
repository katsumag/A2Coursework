package me.katsumag.A2Coursework.components.connections;

import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {

    private final ConnectionNumber inputConnectionNumber;
    private final ConnectionNumber outputConnectionNumber;
    private List<Connection> inputs;
    private Connection output;

    public ConnectionManager(ConnectionNumber inputsNum, ConnectionNumber outputsNum, List<Connection> inputs, Connection output) {
        this.inputConnectionNumber = inputsNum;
        this.outputConnectionNumber = outputsNum;
        this.inputs = inputs;
        this.output = output;
    }

    public ConnectionBuilder modify() {
        // this won't work. Merge this and ConnectionBuilder into one.
        return new ConnectionBuilder(this.inputConnectionNumber, this.outputConnectionNumber, this.inputs, this.output);
    }



}
