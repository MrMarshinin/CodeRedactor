package statements;

public class BlockStatement extends Statement {
    private final StatementList statementList;

    public BlockStatement(StatementList statementList) {
        this.statementList = statementList;
    }

    @Override
    public void doAction() {
        statementList.doAction();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof  BlockStatement)){
            return false;
        } else {
            return statementList.equals(((BlockStatement) obj).statementList);
        }
    }

    public StatementList getStatementList(){
        return statementList;
    }
}
