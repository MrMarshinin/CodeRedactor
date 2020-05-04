package sample;

import statements.ExpressionStatement;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TextRedactor {
    private final Button button;
    private final Label output;
    private TextArea input;
    private TreeComparator treeComparator;


    void showRedactor(Stage primaryStage) {
        primaryStage.setTitle("Code Redactor");
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(input, button, output);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public TextRedactor() {
        button = new Button("Update");
        button.setOnAction(e -> doAction(input.getText()));
//        button.setOnAction(e -> {
//            try {
//                treeComparator.update(input.getText());
//            } catch (IncorrectInputException ignored) {
//            }
//        });
        output = new Label();
        output.setText("Output:");
        input = new TextArea();
        input.setOnKeyPressed(e -> {
            try {
                treeComparator.update(input.getText());
            } catch (IncorrectInputException ignored) {
            }
        });
        treeComparator = new TreeComparator();
    }


    public void doAction(String string) {
        try {
            ExpressionStatement.renewOutput();
            Parser parser = new Parser(string);
            parser.doAction();
            output.setText("Output:");
            for (int i = 0; i < ExpressionStatement.getOutput().size(); i++) {
                output.setText(output.getText() + " " + ExpressionStatement.getOutput().get(i));
            }
        } catch (IncorrectInputException e) {
            output.setText("Output: Invalid input");
        }
    }
}