package statements;

import java.util.ArrayList;

public class PlusMinusExpressionsList extends ConditionExpression {
    private final ArrayList<PlusMinusExpression> plusMinusExpressions = new ArrayList<>();


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


    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof PlusMinusExpressionsList)){
            return false;
        } else if (!(((PlusMinusExpressionsList) obj).plusMinusExpressions.size()
                == plusMinusExpressions.size())){
            return false;
        } else {
            for (int i = 0; i < plusMinusExpressions.size(); i++){
                if (!(plusMinusExpressions.get(i).equals(
                        ((PlusMinusExpressionsList) obj).plusMinusExpressions.get(i)))){
                    return false;
                }
            }
            return true;
        }
    }

}
