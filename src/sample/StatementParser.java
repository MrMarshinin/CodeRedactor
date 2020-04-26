package sample;

import statements.Statement;

public abstract class StatementParser {

    abstract Statement splitStatements(String stringToParse) throws AnotherStatementException;

    abstract void update();

    abstract void doAction();
}
