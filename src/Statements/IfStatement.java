package Statements;

public class IfStatement extends Statement {
private ConditionExpression compareExpression;
private BlockStatement blockStatement;

    public IfStatement(ConditionExpression compareExpression, BlockStatement blockStatement) {
        this.compareExpression = compareExpression;
        this.blockStatement = blockStatement;
    }

    @Override
    public void doAction() {
        if (compareExpression.getAnswer() != 0){
            System.out.println("compareExpression.getAnswer() != 0 blockStatement.size() " + blockStatement.size());
            blockStatement.doAction();
        }
    }

    public int getCompareExpressionAnswer() {
        return compareExpression.getAnswer();
    }

    public int getNumberOfIfStatementsInside(){
        return blockStatement.getNumberOfIfStatements();
    }
}
