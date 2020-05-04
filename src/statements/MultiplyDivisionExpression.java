package statements;

public abstract class MultiplyDivisionExpression extends ConditionExpression {
    SimpleExpression simpleExpression;


    public MultiplyDivisionExpression(SimpleExpression simpleExpression) {
        this.simpleExpression = simpleExpression;
    }


    @Override
    public int getAnswer() {
        return simpleExpression.getAnswer();
    }
}
