package sample;

public class TreeComparator {
    private Tree previousTree;

    public void update(String string) {
        Parser parser = new Parser(string);
        parser.update();

        if (previousTree == null) {
            previousTree = new Tree(parser.getStatementList());
            if (previousTree.containsIfStatement()){
                previousTree.notifyAllIfStatements();
                System.out.println("If statement was added");
            }
            return;
        }

        Tree currentTree = new Tree(parser.getStatementList());

        if (previousTree.wasIfStatementAdded(currentTree)) {
            System.out.println("If statement was added");
            previousTree = currentTree;
            previousTree.notifyAllIfStatements();
        }
    }
}
