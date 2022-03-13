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
    Hand hand;
    Button btnGetHand;
    Button btnRefreshHand;
    Button btnClearText;
    TextArea text;
    Button btnCalculateSumOnHand;
    Button btnGetHeartsFromHand;
    Button btnGetQueenOfSpades;


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

    public void buttonGetHeartsFromHand() {
        this.btnGetHeartsFromHand = new Button();
        btnGetHeartsFromHand.setLayoutX(250);
        btnGetHeartsFromHand.setLayoutY(240);
        btnGetHeartsFromHand.setText("Get hearts");
    }

    public void buttonGetQueenOfSpades() {
        this.btnGetQueenOfSpades = new Button();
        btnGetQueenOfSpades.setLayoutX(350);
        btnGetQueenOfSpades.setLayoutY(240);
        btnGetQueenOfSpades.setText("Check queen of spades");
    }

    public void printFaceAndSuit() {
        List<PlayingCard> listOfHearts = hand.getHeartsFromList();
        if (!listOfHearts.isEmpty()) {
            listOfHearts
                    .stream()
                    .forEach(p ->
                        text.appendText(p.getSuit() + "" + p.getFace() + " ")
                    );
        } else {
            text.appendText("You have no hearts on hand");
        }
        text.appendText("\n");
    }

    public void getQueenOfSpades() {
        text.appendText("Queen of spades in hand: " + hand.isQueenOfSpadesInHand());
    }

    public void clearTextArea() {
        text.deleteText(0, text.getLength());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.hand = new Hand();
        primaryStage.setTitle("Card GUI");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);

        buttonGetHand();
        buttonRefreshHand();
        buttonClearText();
        createATextObject();
        buttonCalculateSumOnHand();
        buttonGetHeartsFromHand();
        buttonGetQueenOfSpades();

        // Register this instance (of self) as listener to button actions
        btnGetHand.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String string = hand.checkHand();
                    text.appendText("Cards drawn: " + hand.getNumberOfCardsToDraw() + " --> ");
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

        btnGetHeartsFromHand.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                printFaceAndSuit();
            }
        });

        btnGetQueenOfSpades.setOnAction(event -> {
            getQueenOfSpades();
        });

        root.getChildren().add(btnGetHand);
        root.getChildren().add(btnRefreshHand);
        root.getChildren().add(btnClearText);
        root.getChildren().add(text);
        root.getChildren().add(btnCalculateSumOnHand);
        root.getChildren().add(btnGetHeartsFromHand);
        root.getChildren().add(btnGetQueenOfSpades);
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