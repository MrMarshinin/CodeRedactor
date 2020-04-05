package Statements;



public class Identifier extends SimpleExpression{
    private String name;
    private static IdentifiersPool identifiersPool;

    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public int getAnswer() {
        return identifiersPool.getIdentifier(this.name).value;
    }

    public boolean hasThisName(String name){
        return this.name.equals(name);
    }

    public void setValue(int value) {
        if (identifiersPool == null){
            identifiersPool = new IdentifiersPool();
        }
        identifiersPool.getIdentifier(this.name).value = value;
    }
}
