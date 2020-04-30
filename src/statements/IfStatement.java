package statements;

public class IfStatement extends Statement {
    private final ConditionExpression compareExpression;
    private final BlockStatement blockStatement;
    private final boolean hasBrackets;


    public IfStatement(ConditionExpression compareExpression, BlockStatement blockStatement, boolean hasBrackets) {
        this.compareExpression = compareExpression;
        this.blockStatement = blockStatement;
        this.hasBrackets = hasBrackets;
    }


    @Override
    public void doAction() {
        if (compareExpression.getAnswer() != 0) {
            blockStatement.doAction();
        }
    }


    public boolean isHasBrackets() {
        return hasBrackets;
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


    public StatementList getStatementList() {
        return blockStatement.getStatementList();
    }


    public boolean isEmpty() {
        return blockStatement.getStatementList().size() == 0;
    }
}
