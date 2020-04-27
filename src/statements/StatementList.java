package statements;

import java.util.ArrayList;

public class StatementList {
    private final ArrayList<Statement> statements;

    public StatementList() {
        this.statements = new ArrayList<>();
    }

    public void doAction() {
        if (!statements.isEmpty()) {
            for (Statement statement : statements) {
                statement.doAction();
            }
        }
    }

    public int size() {
        return statements.size();
    }

    public Statement get(int i) {
        return statements.get(i);
    }

    public void addStatement(Statement statement) {
        statements.add(statement);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof StatementList)){
            return false;
        } else if (!(((StatementList) obj).statements.size()
                == statements.size())){
            return false;
        } else {
            for (int i = 0; i < statements.size(); i++){
                if (!(statements.get(i).equals(((StatementList) obj).statements.get(i)))){
                    return false;
                }
            }
            return true;
        }
    }

    public ArrayList<IfStatement> getInnerIfStatements(){
        ArrayList<IfStatement> ifStatements = new ArrayList<>();
        for (Statement statement : statements){
            if (statement instanceof IfStatement){
                ifStatements.add((IfStatement) statement);
            }
        }
        return ifStatements;
    }
}
