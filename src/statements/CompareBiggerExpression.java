package statements;

public class CompareBiggerExpression extends CompareExpression {
    public CompareBiggerExpression(ConditionExpression firstExpression, ConditionExpression secondExpression) {
        super(firstExpression, secondExpression);
    }


    @Override
    public int getAnswer() {
        return (firstExpression.getAnswer() > secondExpression.getAnswer()) ? 1 : 0;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof CompareBiggerExpression)){
            return false;
        } else {
            if (!(firstExpression.equals(((CompareBiggerExpression) obj).firstExpression))){
                return false;
            } else return secondExpression.equals(((CompareBiggerExpression) obj).secondExpression);
        }
    }
}
