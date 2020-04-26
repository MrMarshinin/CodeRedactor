package sample;

import statements.*;

public class CompareExpressionParser extends ExpressionParser {

    public CompareExpressionParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public CompareExpression update() throws AnotherExpressionException {
        CompareExpression expression;
        int index;
        if (stringToParse.contains(">") || stringToParse.contains("<")) {
            index = Math.max(stringToParse.indexOf("<"), stringToParse.indexOf(">"));
        } else {
            throw new AnotherExpressionException();
        }

        String previousString = stringToParse.substring(0, index);
        String newString = stringToParse.substring(index + 1);

        ConditionExpression previousExpression;
        ConditionExpression newExpression;

        PlusMinusExpressionParser plusMinusExpressionParser1 =
                new PlusMinusExpressionParser(previousString);
        PlusMinusExpressionParser plusMinusExpressionParser2 =
                new PlusMinusExpressionParser(newString);

        previousExpression = plusMinusExpressionParser1.update();
        newExpression = plusMinusExpressionParser2.update();

        if (index == stringToParse.indexOf("<")) {
            expression = new CompareLessExpression(previousExpression, newExpression);
        } else {
            expression = new CompareBiggerExpression(previousExpression, newExpression);
        }
        return expression;
    }
}
