package Statements;

import java.util.ArrayList;

public class MultiplyDivisionExpressionsList extends ConditionExpression {
    private ArrayList<MultiplyDivisionExpression> multiplyDivisionExpressions = new ArrayList<>();
    private SimpleExpression simpleExpression;

    public MultiplyDivisionExpressionsList(SimpleExpression simpleExpression) {
        this.simpleExpression = simpleExpression;
    }

    public void addExpression(MultiplyDivisionExpression multiplyDivisionExpression){
        multiplyDivisionExpressions.add(multiplyDivisionExpression);
    }

    @Override
    public int getAnswer() {
        int answer = simpleExpression.getAnswer();
        for (MultiplyDivisionExpression multiplyDivisionExpression : multiplyDivisionExpressions){
            if (multiplyDivisionExpression instanceof MultiplyExpression){
                answer *= multiplyDivisionExpression.getAnswer();
            } else if (multiplyDivisionExpression instanceof DivisionExpression){
                answer /= multiplyDivisionExpression.getAnswer();
            }
        }
        return answer;
    }
}
