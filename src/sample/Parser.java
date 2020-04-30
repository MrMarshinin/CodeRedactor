package sample;

import statements.*;

public class Parser {
    private StatementList statementList = new StatementList();
    private String stringToParse;


    public Parser(String stringToParse) throws IncorrectInputException {
        stringToParse = stringToParse.replaceAll(" ", "");
        stringToParse = stringToParse.replaceAll("\n", "");
        stringToParse = stringToParse.replaceAll("\t", "");
        this.stringToParse = stringToParse;
        getStatements();
    }


    private void getStatements() throws IncorrectInputException {
        statementList = new StatementList();
        while (!stringToParse.isEmpty()) {
            statementList.addStatement(getNextStatement());
        }
    }


    private Statement getNextStatement() throws IncorrectInputException {
        Statement statement;
        try {
            statement = getIfStatement(stringToParse);
            if (((IfStatement) statement).isHasBrackets()) {
                int index = stringToParse.substring(0, stringToParse.indexOf('{')).length()
                        + getStringOfBlockStatement(stringToParse).length() + 2;
                stringToParse = stringToParse.substring(index);
            }
        } catch (AnotherStatementException e) {
            try {
                statement = getAssignStatement(stringToParse);
                stringToParse = stringToParse.substring(stringToParse.indexOf(';') + 1);
            } catch (AnotherStatementException anotherStatementException) {
                try {
                    statement = getExpressionStatement(stringToParse);
                    stringToParse = stringToParse.substring(stringToParse.indexOf(';') + 1);
                } catch (AnotherStatementException statementException) {
                    throw new IncorrectInputException();
                }
            }
        }
        return statement;
    }


    public StatementList getStatementList() {
        return statementList;
    }


    public void doAction() {
        statementList.doAction();
    }


    private SimpleExpression getSimpleExpression(String stringToParse) throws AnotherExpressionException {
        SimpleExpression expression;
        if (Character.isAlphabetic(stringToParse.charAt(0))) {
            expression = new Identifier(stringToParse);
        } else {
            try {
                expression = new IntegerExpression(stringToParse);
            } catch (NumberFormatException e){
                throw new AnotherExpressionException();
            }
        }
        return expression;
    }


    private MultiplyDivisionExpressionsList getMultiplyDivisionExpressions(String stringToParse) throws AnotherExpressionException {
        MultiplyDivisionExpressionsList expression;
        int index = getMultiplyDivisionIndex(stringToParse);
        if (index != -1) {
            expression = new MultiplyDivisionExpressionsList(getSimpleExpression(stringToParse.substring(0, index)));
            stringToParse = stringToParse.substring(index);
        } else {
            expression = new MultiplyDivisionExpressionsList(getSimpleExpression(stringToParse));
            stringToParse = "";
        }

        while (!stringToParse.isEmpty()) {
            String s = stringToParse.substring(1);
            index = getMultiplyDivisionIndex(1, s);
            String previousString;

            if (index != -1) {
                previousString = stringToParse.substring(0, index);
                stringToParse = stringToParse.substring(index);
            } else {
                previousString = stringToParse;
                stringToParse = "";
            }

            if (previousString.startsWith("/")) {
                expression.addExpression(new DivisionExpression(getSimpleExpression(previousString.substring(1))));
            } else {
                expression.addExpression(new MultiplyExpression(getSimpleExpression(previousString.substring(1))));
            }
        }
        return expression;
    }


    private int getMultiplyDivisionIndex(int index, String stringToParse) {
        if (stringToParse.contains("*") && stringToParse.contains("/")) {
            index += Math.min(stringToParse.indexOf("*"), stringToParse.indexOf("/"));
        } else if (stringToParse.contains("*")) {
            index += stringToParse.indexOf("*");
        } else if (stringToParse.contains("/")) {
            index += stringToParse.indexOf("/");
        } else {
            index = -1;
        }
        return index;
    }


    private int getMultiplyDivisionIndex(String stringToParse) {
        return getMultiplyDivisionIndex(0, stringToParse);
    }


    private PlusMinusExpressionsList getPlusMinusExpressions(String stringToParse) throws AnotherExpressionException {
        PlusMinusExpressionsList expression = new PlusMinusExpressionsList();

        while (!stringToParse.isEmpty()) {
            int index = 0;
            String s = stringToParse;

            if (stringToParse.startsWith("+") || stringToParse.startsWith("-")) {
                s = stringToParse.substring(1);
                index++;
            }
            index = getPlusMinusIndex(index, s);
            String previousString;

            if (index != -1) {
                previousString = stringToParse.substring(0, index);
                stringToParse = stringToParse.substring(index);
            } else {
                previousString = stringToParse;
                stringToParse = "";
            }

            if (previousString.startsWith("-")) {
                expression.addExpression(new MinusExpression(getMultiplyDivisionExpressions(previousString.substring(1))));
            } else if (previousString.startsWith("+")) {
                expression.addExpression(new PlusExpression(getMultiplyDivisionExpressions(previousString.substring(1))));
            } else {
                expression.addExpression(new PlusExpression(getMultiplyDivisionExpressions(previousString)));
            }
        }
        return expression;
    }


    private int getPlusMinusIndex(int index, String s) {
        if (s.contains("+") && s.contains("-")) {
            index += Math.min(s.indexOf("+"), s.indexOf("-"));
        } else if (s.contains("+")) {
            index += s.indexOf("+");
        } else if (s.contains("-")) {
            index += s.indexOf("-");
        } else {
            index = -1;
        }
        return index;
    }


    private CompareExpression getCompareExpression(String stringToParse) throws AnotherExpressionException {
        CompareExpression expression;
        int index;
        if ((stringToParse.contains(">") && !stringToParse.contains("<"))
                || (stringToParse.contains("<") && !stringToParse.contains(">"))) {
            index = Math.max(stringToParse.indexOf("<"), stringToParse.indexOf(">"));
        } else {
            throw new AnotherExpressionException();
        }

        String previousString = stringToParse.substring(0, index);
        String newString = stringToParse.substring(index + 1);

        PlusMinusExpressionsList previousExpression;
        PlusMinusExpressionsList newExpression;

        previousExpression = getPlusMinusExpressions(previousString);
        newExpression = getPlusMinusExpressions(newString);

        if (index == stringToParse.indexOf("<")) {
            expression = new CompareLessExpression(previousExpression, newExpression);
        } else {
            expression = new CompareBiggerExpression(previousExpression, newExpression);
        }
        return expression;
    }


    private ConditionExpression getConditionExpression(String stringToParse) throws AnotherExpressionException {
        ConditionExpression expression;
        try {
            expression = getCompareExpression(stringToParse);
        } catch (AnotherExpressionException e) {
            expression = getPlusMinusExpressions(stringToParse);
        }
        return expression;
    }


    private AssignStatement getAssignStatement(String stringToParse) throws AnotherStatementException {
        if (stringToParse.startsWith("@") && stringToParse.contains("=")
                && stringToParse.contains(";")) {
            Identifier identifier;
            ConditionExpression conditionExpression;
            try {
                identifier = (Identifier) getSimpleExpression(stringToParse.substring(1, stringToParse.indexOf('=')));
                conditionExpression = getConditionExpression((stringToParse.substring(stringToParse.indexOf('=') + 1, stringToParse.indexOf(';'))));
            } catch (AnotherExpressionException e) {
                throw new AnotherStatementException();
            }
            return new AssignStatement(identifier, conditionExpression);
        } else {
            throw new AnotherStatementException();
        }
    }


    private ExpressionStatement getExpressionStatement(String stringToParse) throws AnotherStatementException {
        if (!(stringToParse.startsWith("if")
                && stringToParse.contains("(")
                && stringToParse.contains("}")
                && stringToParse.indexOf('(') < stringToParse.indexOf(')')
                && stringToParse.indexOf(')') + 1 == stringToParse.indexOf('{')
                || stringToParse.startsWith("@") || stringToParse.isEmpty())
                && stringToParse.contains(";")) {
            try {
                return new ExpressionStatement(getConditionExpression(stringToParse.substring(0, stringToParse.indexOf(';'))));
            } catch (AnotherExpressionException e) {
                throw new AnotherStatementException();
            }
        } else {
            throw new AnotherStatementException();
        }
    }


    private BlockStatement getBlockStatement(String stringToParse) throws AnotherStatementException {
        Parser parser;
        try {
            parser = new Parser(stringToParse);
        } catch (IncorrectInputException e) {
            throw new AnotherStatementException();
        }
        return new BlockStatement(parser.getStatementList());
    }


    private IfStatement getIfStatement(String stringToParse) throws AnotherStatementException {
        if (stringToParse.startsWith("if") && stringToParse.contains("(")
                && stringToParse.indexOf('(') < stringToParse.indexOf(')')) {
            ConditionExpression conditionExpression;
            try {
                conditionExpression = getConditionExpression(stringToParse.
                        substring(stringToParse.indexOf('(') + 1, stringToParse.indexOf(')')));
            } catch (AnotherExpressionException e) {
                throw new AnotherStatementException();
            }
            BlockStatement blockStatement;
            if (stringToParse.contains("}") && stringToParse.indexOf(')') + 1 == stringToParse.indexOf('{')) {
                String blockStatementString = getStringOfBlockStatement(stringToParse);
                blockStatement = getBlockStatement(blockStatementString);
                return new IfStatement(conditionExpression, blockStatement, true);
            } else {
                StatementList statementList = new StatementList();
                try {
                    this.stringToParse = stringToParse.substring(stringToParse.indexOf(')') + 1);
                    statementList.addStatement(getNextStatement());
                } catch (IncorrectInputException e) {
                    throw new AnotherStatementException();
                }
                blockStatement = new BlockStatement(statementList);
                return new IfStatement(conditionExpression, blockStatement, false);
            }
        } else {
            throw new AnotherStatementException();
        }
    }


    private String getStringOfBlockStatement(String stringToParse) throws AnotherStatementException {
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
