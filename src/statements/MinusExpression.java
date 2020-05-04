package statements;

public class MinusExpression extends PlusMinusExpression {

    public MinusExpression(ConditionExpression conditionExpression) {
        super(conditionExpression);
    }


    @Override
    public int getAnswer() {
        return  - conditionExpression.getAnswer();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof MinusExpression)){
            return false;
        } else {
            return conditionExpression.equals(((MinusExpression) obj).conditionExpression);
        }
    }

}
