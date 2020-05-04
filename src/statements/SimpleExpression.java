package statements;

public class SimpleExpression extends ConditionExpression {
    protected int value;


    @Override
    public int getAnswer() {
        return value;
    }
}
