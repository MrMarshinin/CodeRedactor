package statements;

public class PlusExpression extends PlusMinusExpression {

    public PlusExpression(ConditionExpression conditionExpression) {
        super(conditionExpression);
    }


    @Override
    public int getAnswer() {
        return conditionExpression.getAnswer();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof PlusExpression)){
            return false;
        } else {
            return conditionExpression.equals(((PlusExpression) obj).conditionExpression);
        }
    }
}
