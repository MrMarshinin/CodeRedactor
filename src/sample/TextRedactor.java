package sample;

import Statements.ExpressionStatement;
import Statements.IfStatement;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TextRedactor {
    private Stage window;
    private Scene scene;
    private Button button;
    private Label output;
    private Label numberOfIfStatements;
    private TextArea input;
    private Parser parser;

    void showRedactor(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Code Redactor");
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(input, button, numberOfIfStatements, output);
        scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }

    public TextRedactor() {
        button = new Button("Update");
        button.setOnAction(e -> doAction(input.getText()));
        output = new Label();
        output.setText("Output:");
        numberOfIfStatements = new Label();
        numberOfIfStatements.setText("Number of if statements: " + 0);
        input = new TextArea();
        input.setOnKeyPressed(e -> buildTree(input.getText()));
    }

    public void update(String string) {
        parser = new Parser(string);
        parser.update();
    }

    public void doAction(String string) {
        ExpressionStatement.renewOutput();
        parser = new Parser(string);
        parser.doAction();
        output.setText("Output:");
        for (int i = 0; i < ExpressionStatement.getOutput().size(); i++) {
            System.out.println(ExpressionStatement.getOutput().get(i));
            output.setText(output.getText() + " " + ExpressionStatement.getOutput().get(i));
        }
    }

    public void buildTree(String string) {
        parser = new Parser(string);
        parser.update();
        numberOfIfStatements.setText("Number of if statements: " + parser.getNumberOfIfStatements());
    }
}