package sample;

import statements.IfStatement;
import statements.Statement;

import java.util.ArrayList;

class WrappedIfStatement {
    private final IfStatement ifStatement;
    private boolean wasNotified;
    private final Statement previousStatement;
    private final Statement nextStatement;
    private final ArrayList<WrappedIfStatement> innerWrappedIfStatements;


    public WrappedIfStatement(IfStatement ifStatement, boolean wasNotified,
                              Statement previousStatement, Statement nextStatement,
                              ArrayList<WrappedIfStatement> innerWrappedIfStatements) {
        this.ifStatement = ifStatement;
        this.wasNotified = wasNotified;
        this.previousStatement = previousStatement;
        this.nextStatement = nextStatement;
        this.innerWrappedIfStatements = innerWrappedIfStatements;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof WrappedIfStatement)) {
            return false;
        } else if (((WrappedIfStatement) obj).innerWrappedIfStatements == null
                && innerWrappedIfStatements == null) {
            return ifStatement.equals(((WrappedIfStatement) obj).ifStatement)
                    && ((WrappedIfStatement) obj).wasNotified == wasNotified;
        } else {
            return ifStatement.equals(((WrappedIfStatement) obj).ifStatement)
                    && ((WrappedIfStatement) obj).wasNotified == wasNotified
                    && ((WrappedIfStatement) obj).innerWrappedIfStatements.equals(innerWrappedIfStatements);
        }
    }


    public boolean wasNotNotified() {
        return !wasNotified;
    }


    public void setWasNotifiedForAllStatements(boolean wasNotified) {
        this.wasNotified = wasNotified;
        for (WrappedIfStatement wrappedIfStatement : innerWrappedIfStatements) {
            wrappedIfStatement.wasNotified = wasNotified;
            wrappedIfStatement.setWasNotifiedForAllStatements(wasNotified);
        }
    }


    public void setWasNotifiedForNotEmptyStatements(boolean wasNotified) {
        if (ifStatement.isEmpty()) {
            return;
        }
        this.wasNotified = wasNotified;
        for (WrappedIfStatement wrappedIfStatement : innerWrappedIfStatements) {
            if (!wrappedIfStatement.ifStatement.isEmpty()){
                wrappedIfStatement.wasNotified = wasNotified;
                wrappedIfStatement.setWasNotifiedForNotEmptyStatements(wasNotified);
            }
        }
    }


    public void setWasNotified(boolean wasNotified) {
        this.wasNotified = wasNotified;
    }


    public IfStatement getIfStatement() {
        return ifStatement;
    }


    public boolean hasSameNeighbours(Tree tree) {
        for (WrappedIfStatement wrappedIfStatement : tree.getWrappedIfStatements()) {
            if (wrappedIfStatement.wasNotified) {
                if (previousStatement == null && nextStatement == null) {
                    if (wrappedIfStatement.previousStatement == null
                            && wrappedIfStatement.nextStatement == null) {
                        return true;
                    }
                } else if (previousStatement == null) {
                    if (wrappedIfStatement.previousStatement == null
                            && nextStatement.equals(wrappedIfStatement.nextStatement)) {
                        return true;
                    }
                } else if (nextStatement == null) {
                    if (previousStatement.equals(wrappedIfStatement.previousStatement)
                            && wrappedIfStatement.nextStatement == null) {
                        return true;
                    }
                } else if (previousStatement.equals(wrappedIfStatement.previousStatement)
                        && nextStatement.equals(wrappedIfStatement.nextStatement)) {
                    return true;
                }

            }
        }
        return false;
    }
}
