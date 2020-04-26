package sample;

import statements.Identifier;
import statements.IntegerExpression;
import statements.SimpleExpression;

public class SimpleExpressionParser extends ExpressionParser {
    public SimpleExpressionParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public SimpleExpression update() {
        SimpleExpression expression;
        if (Character.isAlphabetic(stringToParse.charAt(0))) {
            expression = new Identifier(stringToParse);
        } else {
            expression = new IntegerExpression(stringToParse);
        }
        return expression;
    }
}
