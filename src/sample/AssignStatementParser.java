package sample;

import Statements.AssignStatement;
import Statements.ConditionExpression;
import Statements.Identifier;
import Statements.Statement;

public class AssignStatementParser extends StatementParser {
    private AssignStatement assignStatement;
    private Identifier identifier;
    private ConditionExpression conditionExpression;
    private SimpleExpressionParser simpleExpressionParser;
    private ConditionExpressionParser conditionExpressionParser;

    @Override
    Statement splitStatements(String stringToParse) throws AnotherStatementException {
        if (stringToParse.startsWith("@")) {
            simpleExpressionParser = new SimpleExpressionParser
                    (stringToParse.substring(1, stringToParse.indexOf('=')));
            conditionExpressionParser = new ConditionExpressionParser
                    (stringToParse.substring(stringToParse.indexOf('=') + 1, stringToParse.indexOf(';')));
            assignStatement = new AssignStatement(identifier, conditionExpression);
            return assignStatement;
        } else {
            throw new AnotherStatementException();
        }
    }

    @Override
    void update() {
        identifier = (Identifier) simpleExpressionParser.update();
        conditionExpression = conditionExpressionParser.update();
        assignStatement = new AssignStatement(identifier, conditionExpression);
    }

    @Override
    void doAction() {
        identifier = (Identifier) simpleExpressionParser.update();
        conditionExpression = conditionExpressionParser.update();
        assignStatement = new AssignStatement(identifier, conditionExpression);
        assignStatement.doAction();
    }
}
