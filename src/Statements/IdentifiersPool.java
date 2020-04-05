package Statements;

import java.util.ArrayList;

public class IdentifiersPool {
    private ArrayList<Identifier> identifiers;

    public IdentifiersPool() {
        identifiers = new ArrayList<Identifier>();
    }

    public Identifier getIdentifier(String name) {
        if (!identifiers.isEmpty()) {
            for (Identifier identifier : identifiers) {
                if (identifier.hasThisName(name)) {
                    return identifier;
                }
            }
        }
        Identifier identifier = new Identifier(name);
        identifier.value = 0;
        identifiers.add(identifier);
        return identifier;
    }

    public void addIdentifier(Identifier identifier) {
        identifiers.add(identifier);
    }
}
