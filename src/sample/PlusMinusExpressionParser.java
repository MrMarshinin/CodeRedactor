package sample;

import statements.*;

public class PlusMinusExpressionParser extends ExpressionParser {
    public PlusMinusExpressionParser(String stringToParse) {
        super(stringToParse);
    }

    @Override
    public ConditionExpression update() {
        PlusMinusExpressionsList expression = new PlusMinusExpressionsList();

        while (!stringToParse.isEmpty()) {
            int index = 0;
            String s = stringToParse;

            if (stringToParse.startsWith("+") || stringToParse.startsWith("-")) {
                s = stringToParse.substring(1);
                index++;
            }
            if (s.contains("+") && s.contains("-")) {
                index += Math.min(s.indexOf("+"), s.indexOf("-"));
            } else if (s.contains("+")) {
                index += s.indexOf("+");
            } else if (s.contains("-")) {
                index += s.indexOf("-");
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

            if (previousString.startsWith("-")) {
                MultiplyDivisionExpressionParser multiplyDivisionExpressionParser =
                        new MultiplyDivisionExpressionParser(previousString.substring(1));
                expression.addExpression(new MinusExpression(multiplyDivisionExpressionParser.update()));
            } else if (previousString.startsWith("+")) {
                MultiplyDivisionExpressionParser multiplyDivisionExpressionParser =
                        new MultiplyDivisionExpressionParser(previousString.substring(1));
                expression.addExpression(new PlusExpression(multiplyDivisionExpressionParser.update()));
            } else {
                MultiplyDivisionExpressionParser multiplyDivisionExpressionParser =
                        new MultiplyDivisionExpressionParser(previousString);
                expression.addExpression(new PlusExpression(multiplyDivisionExpressionParser.update()));
            }
        }
        return expression;
    }

}
