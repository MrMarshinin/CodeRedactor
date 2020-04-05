package Statements;

public class IntegerExpression extends SimpleExpression {

    public IntegerExpression(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public int getAnswer() {
        return value;
    }
}
