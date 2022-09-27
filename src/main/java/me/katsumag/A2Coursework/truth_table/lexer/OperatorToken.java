package me.katsumag.A2Coursework.truth_table.lexer;

public class OperatorToken implements Token {

    private final String operationType;

    public OperatorToken(String type) {
        this.operationType = type;
    }

    public String getOperationType() { return this.operationType; }

    @Override
    public String toString() {
        return String.format("OperatorToken[type=%s]", this.operationType);
    }

}
