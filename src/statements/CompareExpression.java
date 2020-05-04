package statements;

public abstract class CompareExpression extends ConditionExpression {
    ConditionExpression firstExpression;
    ConditionExpression secondExpression;


    public CompareExpression(ConditionExpression firstExpression, ConditionExpression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }
}
