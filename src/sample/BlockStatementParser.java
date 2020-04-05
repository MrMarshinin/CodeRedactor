package sample;

import Statements.BlockStatement;

public class BlockStatementParser extends StatementParser {
    private BlockStatement blockStatement;
    private Parser parser;

    @Override
    BlockStatement splitStatements(String stringToParse) {
        parser = new Parser(stringToParse);
        update();
        System.out.println("Size BlockStatement " + parser.getStatementList().size());
        blockStatement = new BlockStatement(parser.getStatementList());
        return blockStatement;
    }

    @Override
    void update() {
        parser.update();
    }

    @Override
    void doAction() {
        parser.update();
        blockStatement.doAction();
    }
}