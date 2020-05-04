package sample;

import statements.IfStatement;
import statements.StatementList;

import java.util.ArrayList;


public class Tree {
    private final StatementList statementList;
    private final ArrayList<WrappedIfStatement> wrappedIfStatements;


    public Tree(StatementList statementList) {
        this.statementList = statementList;
        this.wrappedIfStatements = getIfStatements(statementList);
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


    public boolean wasIfStatementAdded(Tree newTree) {
        boolean wasIfStatementAdded = false;
        newTree.notifyExistingNotEmptyIfStatements(this);
        for (WrappedIfStatement wrappedIfStatement : newTree.wrappedIfStatements) {
            if (wrappedIfStatement.wasNotNotified()) {
                if (!wrappedIfStatement.hasSameNeighbours(this)) {
                    if (!wrappedIfStatement.getIfStatement().isEmpty()) {
                        wasIfStatementAdded = true;
                    }
                }
            }
        }
        return wasIfStatementAdded;
    }


    private ArrayList<WrappedIfStatement> getIfStatements(StatementList statementList) {
        ArrayList<WrappedIfStatement> thisList = new ArrayList<>();
        for (int i = 0; i < statementList.size(); i++) {
            if (statementList.get(i) instanceof IfStatement) {
                ArrayList<WrappedIfStatement> innerArrayList =
                        getIfStatements(((IfStatement) statementList.get(i)).getStatementList());
                thisList.add(new WrappedIfStatement((IfStatement) statementList.get(i), false,
                        (i == 0) ? null : statementList.get(i - 1),
                        (i == statementList.size() - 1) ? null : statementList.get(i + 1),
                        innerArrayList));
                thisList.addAll(innerArrayList);
            }
        }
        return thisList;
    }


    public void notifyAllIfStatements(boolean wasNotified) {
        for (WrappedIfStatement wrappedIfStatement : wrappedIfStatements) {
            wrappedIfStatement.setWasNotifiedForAllStatements(wasNotified);
        }
    }


    public void notifyNotEmptyIfStatements(boolean wasNotified) {
        for (WrappedIfStatement wrappedIfStatement : wrappedIfStatements) {
            if (!wrappedIfStatement.getIfStatement().isEmpty()) {
                wrappedIfStatement.setWasNotifiedForNotEmptyStatements(wasNotified);
            }
        }
    }


    public boolean containsNotEmptyIfStatement() {
        for (int i = 0; i < statementList.size(); i++) {
            if (statementList.get(i) instanceof IfStatement
                    && !((IfStatement) statementList.get(i)).isEmpty()) {
                return true;
            }
        }
        return false;
    }


    public void notifyExistingNotEmptyIfStatements(Tree tree) {
        notifyAllIfStatements(false);
        for (WrappedIfStatement wrappedIfStatement : tree.wrappedIfStatements) {
            for (WrappedIfStatement wrappedIfStatement1 : wrappedIfStatements) {
                if (wrappedIfStatement.getIfStatement().equals(wrappedIfStatement1.getIfStatement())
                        && wrappedIfStatement1.wasNotNotified()
                        && !wrappedIfStatement1.getIfStatement().isEmpty()) {
                    wrappedIfStatement1.setWasNotified(true);
                    break;
                }
            }
        }
    }


    public ArrayList<WrappedIfStatement> getWrappedIfStatements() {
        return wrappedIfStatements;
    }
}
