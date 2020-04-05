package Statements;

public class PlusExpression extends PlusMinusExpression {

    public PlusExpression(ConditionExpression conditionExpression) {
        super(conditionExpression);
    }

    @Override
    public int getAnswer() {
        return conditionExpression.getAnswer();
    }
}
