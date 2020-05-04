package statements;

import java.util.ArrayList;

public class IdentifiersPool {
    private final ArrayList<Identifier> identifiers;


    public IdentifiersPool() {
        identifiers = new ArrayList<>();
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
}
