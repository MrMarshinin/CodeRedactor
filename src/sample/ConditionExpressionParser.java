package sample;

import Statements.ConditionExpression;

public class ConditionExpressionParser extends ExpressionParser {
    public ConditionExpressionParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public ConditionExpression update() {
        ConditionExpression expression;
        try {
            CompareExpressionParser compareExpressionParser =
                    new CompareExpressionParser(stringToParse);
            expression = compareExpressionParser.update();
        } catch (AnotherExpressionException e) {
            PlusMinusExpressionParser plusMinusExpressionParser =
                    new PlusMinusExpressionParser(stringToParse);
            expression = plusMinusExpressionParser.update();
        }
        return expression;
    }
}
