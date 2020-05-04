package statements;

public class MultiplyExpression extends MultiplyDivisionExpression {
    public MultiplyExpression(SimpleExpression simpleExpression) {
        super(simpleExpression);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof MultiplyExpression)){
            return false;
        } else {
            return simpleExpression.equals(((MultiplyExpression) obj).simpleExpression);
        }
    }

}
