package Statements;

public class MinusExpression extends PlusMinusExpression {

    public MinusExpression(ConditionExpression conditionExpression) {
        super(conditionExpression);
    }

    @Override
    public int getAnswer() {
        return  - conditionExpression.getAnswer();
    }
}
