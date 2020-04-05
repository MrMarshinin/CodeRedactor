package Statements;

public class CompareBiggerExpression extends CompareExpression {
    public CompareBiggerExpression(ConditionExpression firstExpression, ConditionExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public int getAnswer() {
        return (firstExpression.getAnswer() > secondExpression.getAnswer()) ? 1 : 0;
    }
}
