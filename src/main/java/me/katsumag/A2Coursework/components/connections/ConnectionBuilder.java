package me.katsumag.A2Coursework.components.connections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConnectionBuilder {

    private ConnectionNumber numberOfInputs, numberOfOutputs;
    private List<Connection> inputs = new ArrayList<>();
    private Connection output;

    public ConnectionBuilder() {}

    public ConnectionBuilder(ConnectionNumber numberOfInputs, ConnectionNumber numberOfOutputs, List<Connection> inputs, Connection output) {
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        this.inputs = inputs;
        this.output = output;
    }

    public ConnectionBuilder setNumberOfInputs(ConnectionNumber numberOfInputs) {
        this.numberOfInputs = numberOfInputs;
        return this;
    }

    public ConnectionBuilder setNumberOfOutputs(ConnectionNumber numberOfOutputs) {
        this.numberOfOutputs = numberOfOutputs;
        return this;
    }

    public ConnectionBuilder addInputs(Connection... connections) {
        this.inputs.addAll(Arrays.asList(connections));
        return this;
    }

    public ConnectionBuilder setOutput(Connection connection) {
        this.output = connection;
        return this;
    }

    public ConnectionManager build() {
        return new ConnectionManager(this.numberOfInputs, this.numberOfOutputs, this.inputs, this.output);
    }

}
