package Statements;

public class SimpleExpression extends ConditionExpression {
    int value;

    @Override
    public int getAnswer() {
        return value;
    }
}
