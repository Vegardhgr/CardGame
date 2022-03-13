package no.ntnu.idatg2001.oblig3.cardGame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.nio.file.attribute.GroupPrincipal;
import java.util.List;

public class Gui extends Application {
    Button btnGetHand;
    Button btnRefreshHand;
    Button btnClearText;
    TextArea text;
    Button btnCalculateSumOnHand;


    public void buttonGetHand() {
        this.btnGetHand = new Button();
        btnGetHand.setLayoutX(350);
        btnGetHand.setLayoutY(200);
        btnGetHand.setText("Get hand");
    }

    public void buttonRefreshHand() {
        this.btnRefreshHand = new Button();
        btnRefreshHand.setLayoutX(250);
        btnRefreshHand.setLayoutY(200);
        btnRefreshHand.setText("Refresh hand");
    }

    public void buttonClearText() {
        this.btnClearText = new Button();
        btnClearText.setLayoutX(100);
        btnClearText.setLayoutY(200);
        btnClearText.setText("Clear text window");
    }

    public void createATextObject() {
        this.text = new TextArea();
    }

    public void buttonCalculateSumOnHand() {
        this.btnCalculateSumOnHand = new Button();
        btnCalculateSumOnHand.setLayoutX(100);
        btnCalculateSumOnHand.setLayoutY(240);
        btnCalculateSumOnHand.setText("Calculate sum");
    }

    public void clearTextArea() {
        text.deleteText(0, text.getLength());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Hand hand = new Hand();
        primaryStage.setTitle("Card GUI");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);
        buttonGetHand();
        buttonRefreshHand();
        buttonClearText();
        createATextObject();
        buttonCalculateSumOnHand();

        // Register this instance (of self) as listener to button actions
        btnGetHand.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String string = hand.checkHand();
                    if (!(string == "")) {
                        text.appendText(string + "\n");
                    } else {
                        text.appendText("Not flush\n");
                    }
                }
                catch (IllegalArgumentException e){
                    text.appendText(e.getMessage() + "\n");
                }
            }
        });

        btnRefreshHand.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hand.makeANewDeck();
                text.appendText("Hand is now refreshed\n");
            }
        });

        btnClearText.setOnAction(event ->
                clearTextArea()
        );

        btnCalculateSumOnHand.setOnAction(event ->
                text.appendText("" + hand.sumOfFaceOnHand() + "\n")
        );

        root.getChildren().add(btnGetHand);
        root.getChildren().add(btnRefreshHand);
        root.getChildren().add(btnClearText);
        root.getChildren().add(text);
        root.getChildren().add(btnCalculateSumOnHand);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
