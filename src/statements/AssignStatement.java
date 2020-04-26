package statements;

public class AssignStatement extends Statement {
    private Identifier identifier;
    private ConditionExpression conditionExpression;

    public AssignStatement(Identifier identifier, ConditionExpression conditionExpression) {
        this.identifier = identifier;
        this.conditionExpression = conditionExpression;
    }

    @Override
    public void doAction() {
        identifier.setValue(conditionExpression.getAnswer());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof AssignStatement)){
            return false;
        } else {
            if (!(identifier.equals(((AssignStatement) obj).identifier))){
                return false;
            } else return conditionExpression.equals(((AssignStatement) obj).conditionExpression);
        }
    }
}
