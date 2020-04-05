package sample;

import Statements.StatementList;

import java.util.ArrayList;

public class Parser {
    private StatementList statementList = new StatementList();
    private String stringToParse;
    private ArrayList<StatementParser> statementParsers = new ArrayList<>();

    public Parser(String stringToParse) {
        stringToParse = stringToParse.replaceAll(" ", "");
        stringToParse = stringToParse.replaceAll("\n", "");
        stringToParse = stringToParse.replaceAll("\t", "");
        this.stringToParse = stringToParse;
        getStatements();
    }

    public void getStatements() {
        statementList = new StatementList();
        while (!stringToParse.isEmpty()) {
            try {
                StatementParser ifStatementParser = new IfStatementParser();
                this.statementList.addStatement(ifStatementParser.splitStatements(stringToParse));
                stringToParse = stringToParse.substring(stringToParse.indexOf('{') + 1);

                int numberOfBracketsInside = 0;
                for (int i = 0; i < stringToParse.length(); i++) {
                    if (stringToParse.charAt(i) == '{') {
                        numberOfBracketsInside++;
                    } else if (stringToParse.charAt(i) == '}') {
                        if (numberOfBracketsInside == 0) {
                            stringToParse = stringToParse.substring(i + 1);
                            break;
                        } else {
                            numberOfBracketsInside--;
                        }
                    }
                }

                statementParsers.add(ifStatementParser);
            } catch (AnotherStatementException e) {
                try {
                    StatementParser assignStatementParser = new AssignStatementParser();
                    this.statementList.addStatement(assignStatementParser.splitStatements(stringToParse));
                    stringToParse = stringToParse.substring(stringToParse.indexOf(';') + 1);
                    statementParsers.add(assignStatementParser);
                } catch (AnotherStatementException ex) {
                    try {
                        StatementParser expressionStatementParser = new ExpressionStatementParser();
                        this.statementList.addStatement(expressionStatementParser.splitStatements(stringToParse));
                        stringToParse = stringToParse.substring(stringToParse.indexOf(';') + 1);
                        statementParsers.add(expressionStatementParser);
                    } catch (AnotherStatementException exc) {
                        stringToParse = stringToParse.substring(1);
                    }
                }
            }
        }
    }

    public StatementList getStatementList() {
        return statementList;
    }

    public void update() {
        for (int i = 0; i < statementParsers.size(); i++) {
            statementParsers.get(i).update();
        }
    }

    public void doAction() {
        for (int i = 0; i < statementParsers.size(); i++){
            statementParsers.get(i).doAction();
        }
    }

    public int getNumberOfIfStatements() {
        return statementList.getNumberOfIfStatements();
    }

}
