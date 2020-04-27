package statements;

import java.util.ArrayList;

public class MultiplyDivisionExpressionsList extends ConditionExpression {
    private final ArrayList<MultiplyDivisionExpression> multiplyDivisionExpressions = new ArrayList<>();
    private final SimpleExpression simpleExpression;

    public MultiplyDivisionExpressionsList(SimpleExpression simpleExpression) {
        this.simpleExpression = simpleExpression;
    }

    public void addExpression(MultiplyDivisionExpression multiplyDivisionExpression) {
        multiplyDivisionExpressions.add(multiplyDivisionExpression);
    }

    @Override
    public int getAnswer() {
        int answer = simpleExpression.getAnswer();
        for (MultiplyDivisionExpression multiplyDivisionExpression : multiplyDivisionExpressions) {
            if (multiplyDivisionExpression instanceof MultiplyExpression) {
                answer *= multiplyDivisionExpression.getAnswer();
            } else if (multiplyDivisionExpression instanceof DivisionExpression) {
                answer /= multiplyDivisionExpression.getAnswer();
            }
        }
        return answer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof MultiplyDivisionExpressionsList)) {
            return false;
        } else if (!(((MultiplyDivisionExpressionsList) obj).multiplyDivisionExpressions.size()
                == multiplyDivisionExpressions.size())) {
            return false;
        } else {
            if (simpleExpression.equals(((MultiplyDivisionExpressionsList) obj).simpleExpression)) {
                for (int i = 0; i < multiplyDivisionExpressions.size(); i++) {
                    if (!(multiplyDivisionExpressions.get(i).equals(
                            ((MultiplyDivisionExpressionsList) obj).multiplyDivisionExpressions.get(i)))) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

}
