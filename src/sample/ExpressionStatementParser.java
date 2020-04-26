package sample;

import statements.ConditionExpression;
import statements.ExpressionStatement;
import statements.Statement;

public class ExpressionStatementParser extends StatementParser {
    private ConditionExpression conditionExpression;
    private ConditionExpressionParser conditionExpressionParser;
    private ExpressionStatement expressionStatement;

    @Override
    Statement splitStatements(String stringToParse) throws AnotherStatementException {
        if (!(stringToParse.startsWith("if")
                && stringToParse.contains("(")
                && stringToParse.contains("}")
                && stringToParse.indexOf('(') < stringToParse.indexOf(')')
                && stringToParse.indexOf(')') + 1 == stringToParse.indexOf('{')
                || stringToParse.startsWith("@") || stringToParse.isEmpty())
                && stringToParse.contains(";")) {
            conditionExpressionParser = new ConditionExpressionParser(stringToParse.substring(0, stringToParse.indexOf(';')));
            expressionStatement = new ExpressionStatement(conditionExpressionParser.update());
            return expressionStatement;
        } else {
            throw new AnotherStatementException();
        }
    }

    @Override
    void update() {
        conditionExpression = conditionExpressionParser.update();
        expressionStatement = new ExpressionStatement(conditionExpression);
    }

    @Override
    void doAction() {
        conditionExpression = conditionExpressionParser.update();
        expressionStatement = new ExpressionStatement(conditionExpression);
        expressionStatement.doAction();
    }
}