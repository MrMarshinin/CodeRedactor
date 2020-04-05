package Statements;

public class BlockStatement extends Statement {
    private StatementList statementList;

    public BlockStatement(StatementList statementList) {
        this.statementList = statementList;
    }

    public BlockStatement() {
        this(new StatementList());
    }

    @Override
    public void doAction() {
        System.out.println("BlockStatement doAction statementList.size() " + statementList.size());
        statementList.doAction();
    }

    public int size() {
        return statementList.size();
    }

    public Statement get(int i) {
        return statementList.get(i);
    }

    public boolean isEmpty() {
        return statementList.isEmpty();
    }

    public void addStatement(Statement statement) {
        statementList.addStatement(statement);
    }

    public int getNumberOfIfStatements() {
        return statementList.getNumberOfIfStatements();
    }

}
