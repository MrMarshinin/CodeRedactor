package sample;

import Statements.BlockStatement;
import Statements.ConditionExpression;
import Statements.IfStatement;
import Statements.Statement;

public class IfStatementParser extends StatementParser {
    private ConditionExpressionParser conditionExpressionParser;
    private BlockStatementParser blockStatementParser;
    private ConditionExpression conditionExpression;
    private BlockStatement blockStatement;
    private IfStatement ifStatement;

    @Override
    Statement splitStatements(String stringToParse) throws AnotherStatementException {
        if (stringToParse.startsWith("if")
                && stringToParse.contains("(")
                && stringToParse.contains("}")
                && stringToParse.indexOf('(') < stringToParse.indexOf(')')
                && stringToParse.indexOf(')') + 1 == stringToParse.indexOf('{')) {

            String blockStatementString = getStringOfBlockStatement(stringToParse);

            conditionExpression = new ConditionExpression();
            conditionExpressionParser = new ConditionExpressionParser(stringToParse.substring(stringToParse.indexOf('(') + 1,
                    stringToParse.indexOf(')')));
            conditionExpression = conditionExpressionParser.update();
            blockStatementParser = new BlockStatementParser();
            blockStatement = blockStatementParser.splitStatements(blockStatementString);
            blockStatementParser.update();
            ifStatement = new IfStatement(conditionExpression, blockStatement);
            return ifStatement;
        } else {
            throw new AnotherStatementException();
        }
    }

    @Override
    void update() {
        conditionExpression = conditionExpressionParser.update();
        blockStatementParser.update();
    }

    @Override
    void doAction() {
        conditionExpression = conditionExpressionParser.update();
        blockStatementParser.update();
        ifStatement = new IfStatement(conditionExpression, blockStatement);
        ifStatement.doAction();
    }


    public String getStringOfBlockStatement(String stringToParse) throws AnotherStatementException {
        if (stringToParse.startsWith("if")
                && stringToParse.contains("(")
                && stringToParse.contains("}")
                && stringToParse.indexOf('(') < stringToParse.indexOf(')')
                && stringToParse.indexOf(')') + 1 == stringToParse.indexOf('{')) {

            String blockStatementString = stringToParse.substring(stringToParse.indexOf('{') + 1);

            int numberOfBracketsInside = 0;

            for (int i = 0; i < blockStatementString.length(); i++) {
                if (blockStatementString.charAt(i) == '{') {
                    numberOfBracketsInside++;
                } else if (blockStatementString.charAt(i) == '}') {
                    if (numberOfBracketsInside == 0) {
                        blockStatementString = blockStatementString.substring(0, i);
                        return blockStatementString;
                    } else {
                        numberOfBracketsInside--;
                    }
                }
            }
        } else {
            throw new AnotherStatementException();
        }
        throw new AnotherStatementException();
    }

}
