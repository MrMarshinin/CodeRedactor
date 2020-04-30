package sample;

public class TreeComparator {
    private Tree previousTree;

    public void update(String string) throws IncorrectInputException {
        Parser parser = new Parser(string);

        if (previousTree == null) {
            previousTree = new Tree(parser.getStatementList());
            if (previousTree.containsIfStatement()) {
                previousTree.notifyAllIfStatements();
                System.out.println("If statement was added");
            }
            return;
        }

        Tree currentTree = new Tree(parser.getStatementList());

        if (previousTree.wasIfStatementAdded(currentTree)) {
            System.out.println("If statement was added");
        }

        currentTree.notifyAllIfStatements();
        previousTree = currentTree;
    }
}
