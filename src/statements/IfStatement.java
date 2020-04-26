package statements;

public class IfStatement extends Statement {
    private ConditionExpression compareExpression;
    private BlockStatement blockStatement;

    public IfStatement(ConditionExpression compareExpression, BlockStatement blockStatement) {
        this.compareExpression = compareExpression;
        this.blockStatement = blockStatement;
    }

    @Override
    public void doAction() {
        if (compareExpression.getAnswer() != 0) {
            blockStatement.doAction();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof IfStatement)) {
            return false;
        } else {
            if (!((IfStatement) obj).compareExpression.equals(compareExpression)) {
                return false;
            } else return ((IfStatement) obj).blockStatement.equals(blockStatement);
        }
    }
}
