package Statements;

import java.util.ArrayList;

public class PlusMinusExpressionsList extends ConditionExpression {
    private ArrayList<PlusMinusExpression> plusMinusExpressions = new ArrayList<PlusMinusExpression>();

    public void addExpression(PlusMinusExpression plusMinusExpression){
        plusMinusExpressions.add(plusMinusExpression);
    }

    @Override
    public int getAnswer() {
        int answer = 0;
        for (PlusMinusExpression plusMinusExpression : plusMinusExpressions){
            answer += plusMinusExpression.getAnswer();
        }
        return answer;
    }
}
