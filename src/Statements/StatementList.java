package Statements;

import java.util.ArrayList;

public class StatementList {
    private boolean isEmpty;
    private ArrayList<Statement> statements;

    public StatementList(ArrayList<Statement> statements) {
        this.statements = statements;
        this.isEmpty = statements.isEmpty();
    }

    public StatementList() {
        this.statements = new ArrayList<Statement>();
        this.isEmpty = true;
    }

    public void doAction() {
        if (!statements.isEmpty()) {
            for (int i = 0; i < statements.size(); i++) {
                 if (statements.get(i) instanceof IfStatement){
                }
                statements.get(i).doAction();
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
        this.isEmpty = false;
    }

    public void addAllStatements(StatementList statementList) {
        for (int i = 0; i < statementList.size(); i++) {
            addStatement(statementList.get(i));
        }
    }

    public int getNumberOfIfStatements() {
        int i = 0;
        for (Statement statement : statements) {
            if (statement instanceof IfStatement) {
                i += ((IfStatement) statement).getNumberOfIfStatementsInside();
                i++;
            }
        }
        return i;
    }

    public boolean isEmpty() {
        return statements.isEmpty();
    }
}
