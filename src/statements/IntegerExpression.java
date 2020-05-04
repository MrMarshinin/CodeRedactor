package statements;

public class IntegerExpression extends SimpleExpression {

    public IntegerExpression(String value) {
        this.value = Integer.parseInt(value);
    }


    @Override
    public int getAnswer() {
        return value;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof IntegerExpression)) {
            return false;
        } else {
            return value == ((IntegerExpression) obj).value;
        }
    }
}
