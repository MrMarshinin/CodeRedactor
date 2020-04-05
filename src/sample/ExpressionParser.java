package sample;

import Statements.Expression;

public abstract class ExpressionParser{
    protected String stringToParse;

    public ExpressionParser(String stringToParse) {
        this.stringToParse = stringToParse;
    }

    public abstract Expression update() throws AnotherExpressionException;
}
