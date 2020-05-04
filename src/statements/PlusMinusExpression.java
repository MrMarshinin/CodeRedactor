package statements;

public abstract class PlusMinusExpression extends ConditionExpression {
    ConditionExpression conditionExpression;


    public PlusMinusExpression(ConditionExpression conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

}
