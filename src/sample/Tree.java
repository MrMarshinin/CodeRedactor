package sample;

import statements.IfStatement;
import statements.StatementList;

import java.util.ArrayList;


public class Tree {
    private final StatementList statementList;
    private final ArrayList<WrappedIfStatement> wrappedIfStatements;

    public Tree(StatementList statementList) {
        this.statementList = statementList;
        this.wrappedIfStatements = getIfStatements();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Tree)) {
            return false;
        } else if (statementList.size() != ((Tree) obj).statementList.size()) {
            return false;
        } else {
            return statementList.equals(((Tree) obj).statementList);
        }
    }

    private static class WrappedIfStatement {
        private final IfStatement ifStatement;
        private boolean wasNotified;

        public WrappedIfStatement(IfStatement ifStatement, boolean wasNotified) {
            this.ifStatement = ifStatement;
            this.wasNotified = wasNotified;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            } else if (!(obj instanceof WrappedIfStatement)) {
                return false;
            } else {
                return ifStatement.equals(((WrappedIfStatement) obj).ifStatement)
                        && ((WrappedIfStatement) obj).wasNotified == wasNotified;
            }
        }
    }

    /**
     * Returns true if newTree contains IfStatement that was not notified neither in newTree nor in this.
     */
    public boolean wasIfStatementAdded(Tree newTree) {
        boolean wasIfStatementAdded = false;
        newTree.notifyExistingIfStatements(this);
        for (WrappedIfStatement wrappedIfStatement : newTree.wrappedIfStatements) {
            if (!wrappedIfStatement.wasNotified) {
                wasIfStatementAdded = true;
                break;
            }
        }
        notifyAllIfStatements();
        return wasIfStatementAdded;
    }

    private ArrayList<WrappedIfStatement> getIfStatements() {
        if (wrappedIfStatements == null) {
            ArrayList<WrappedIfStatement> thisLIst = new ArrayList<>();
            for (int i = 0; i < statementList.size(); i++) {
                if (statementList.get(i) instanceof IfStatement) {
                    thisLIst.add(new WrappedIfStatement((IfStatement) statementList.get(i), false));
                }
            }
            return thisLIst;
        } else {
            return wrappedIfStatements;
        }
    }

    public boolean containsIfStatement() {
        for (int i = 0; i < statementList.size(); i++) {
            if (statementList.get(i) instanceof IfStatement) {
                return true;
            }
        }
        return false;
    }

    public void notifyAllIfStatements() {
        for (WrappedIfStatement wrappedIfStatement : wrappedIfStatements) {
            wrappedIfStatement.wasNotified = true;
        }
    }

    public void notifyExistingIfStatements(Tree tree) {
        for (WrappedIfStatement wrappedIfStatement : wrappedIfStatements) {
            wrappedIfStatement.wasNotified = false;
        }

        for (WrappedIfStatement wrappedIfStatement : tree.wrappedIfStatements) {
            for (WrappedIfStatement wrappedIfStatement1 : wrappedIfStatements) {
                if (wrappedIfStatement.ifStatement.equals(wrappedIfStatement1.ifStatement)
                        && !wrappedIfStatement1.wasNotified) {
                    wrappedIfStatement1.wasNotified = true;
                    break;
                }
            }
        }
    }
}
