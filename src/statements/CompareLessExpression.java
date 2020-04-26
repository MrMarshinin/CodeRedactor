package statements;

public class CompareLessExpression extends CompareExpression {
    public CompareLessExpression(ConditionExpression firstExpression, ConditionExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public int getAnswer() {
        return (firstExpression.getAnswer() < secondExpression.getAnswer()) ? 1 : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof CompareLessExpression)){
            return false;
        } else {
            if (!(firstExpression.equals(((CompareLessExpression) obj).firstExpression))){
                return false;
            } else return secondExpression.equals(((CompareLessExpression) obj).secondExpression);
        }
    }

}
