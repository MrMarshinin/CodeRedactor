package Statements;

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
}
