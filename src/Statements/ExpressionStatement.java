package Statements;

import java.util.ArrayList;

public class ExpressionStatement extends Statement {
    private static ArrayList<Integer> output = new ArrayList<>();
    private ConditionExpression expression;

    public ExpressionStatement(ConditionExpression expression) {
        this.expression = expression;
    }

    @Override
    public void doAction() {
        output.add(expression.getAnswer());
    }

    public static ArrayList<Integer> getOutput() {
        return output;
    }

    public static void renewOutput() {
        ExpressionStatement.output = new ArrayList<Integer>();
    }
}
