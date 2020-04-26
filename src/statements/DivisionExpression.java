package statements;

public class DivisionExpression extends MultiplyDivisionExpression {
    public DivisionExpression(SimpleExpression simpleExpression) {
        super(simpleExpression);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof DivisionExpression)){
            return false;
        } else {
            return simpleExpression.equals(((DivisionExpression) obj).simpleExpression);
        }
    }
}
