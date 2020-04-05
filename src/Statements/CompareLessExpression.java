package Statements;

public class CompareLessExpression extends CompareExpression {
    public CompareLessExpression(ConditionExpression firstExpression, ConditionExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public int getAnswer() {
        return (firstExpression.getAnswer() < secondExpression.getAnswer()) ? 1 : 0;
    }
}
