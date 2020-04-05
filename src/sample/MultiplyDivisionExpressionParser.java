package sample;

import Statements.*;

public class MultiplyDivisionExpressionParser extends ExpressionParser {
    public MultiplyDivisionExpressionParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public ConditionExpression update() {
        MultiplyDivisionExpressionsList expression;
        int index = 0;
        if (stringToParse.contains("*") && stringToParse.contains("/")) {
            index = Math.min(stringToParse.indexOf("*"), stringToParse.indexOf("/"));
        } else if (stringToParse.contains("*")) {
            index = stringToParse.indexOf("*");
        } else if (stringToParse.contains("/")) {
            index = stringToParse.indexOf("/");
        } else {
            index = -1;
        }

        if (index != -1) {
            SimpleExpressionParser simpleExpressionParser = new SimpleExpressionParser(stringToParse.substring(0, index));
            this.stringToParse = stringToParse.substring(index);
            expression = new MultiplyDivisionExpressionsList(simpleExpressionParser.update());
        } else {
            SimpleExpressionParser simpleExpressionParser = new SimpleExpressionParser(stringToParse);
            this.stringToParse = "";
            expression = new MultiplyDivisionExpressionsList(simpleExpressionParser.update());
        }

        while (!stringToParse.isEmpty()) {
            index = 1;
            String s = stringToParse.substring(1);

            if (s.contains("*") && s.contains("/")) {
                index += Math.min(s.indexOf("*"), s.indexOf("/"));
            } else if (s.contains("*")) {
                index += s.indexOf("*");
            } else if (s.contains("/")) {
                index += s.indexOf("/");
            } else {
                index = -1;
            }

            String previousString;

            if (index != -1) {
                previousString = stringToParse.substring(0, index);
                this.stringToParse = stringToParse.substring(index);
            } else {
                previousString = stringToParse;
                this.stringToParse = "";
            }

            if (previousString.startsWith("/")) {
                SimpleExpressionParser simpleExpressionParser = new SimpleExpressionParser(previousString.substring(1));
                expression.addExpression(new DivisionExpression(simpleExpressionParser.update()));
            } else {
                SimpleExpressionParser simpleExpressionParser = new SimpleExpressionParser(previousString.substring(1));
                expression.addExpression(new MultiplyExpression(simpleExpressionParser.update()));
            }
        }
        return expression;
    }

}
