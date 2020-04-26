package statements;

import java.util.ArrayList;

public class ExpressionStatement extends Statement {
    private static ArrayList<Integer> output = new ArrayList<>();
    private final ConditionExpression expression;

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
        ExpressionStatement.output = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof ExpressionStatement)){
            return false;
        } else {
            return expression.equals(((ExpressionStatement) obj).expression);
        }
    }

}
