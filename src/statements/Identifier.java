package statements;



public class Identifier extends SimpleExpression{
    private final String name;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof Identifier)){
            return false;
        } else {
            return name.equals(((Identifier) obj).name);
        }
    }
}
