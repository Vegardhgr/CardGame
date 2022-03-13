package no.ntnu.idatg2001.oblig3.cardGame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.util.List;

/**
 * This class is the GUI of the card game
 *
 * @author Vegard Grøder
 * @version 1.0.0
 */
public class Gui extends Application {
    // Global fields
    Hand hand;
    Button btnGetHand;
    Button btnRefreshHand;
    Button btnClearText;
    Button btnCheckHand;
    TextArea text;

    /**
     * Button for get hand
     */
    public void buttonGetHand() {
        this.btnGetHand = new Button();
        btnGetHand.setLayoutX(100);
        btnGetHand.setLayoutY(200);
        btnGetHand.setText("Get hand");
    }

    /**
     * Button to get a new deck of cards
     */
    public void buttonRefreshHand() {
        this.btnRefreshHand = new Button();
        btnRefreshHand.setLayoutX(300);
        btnRefreshHand.setLayoutY(240);
        btnRefreshHand.setText("Refresh hand");
    }

    /**
     * Button to clear the text window
     */
    public void buttonClearText() {
        this.btnClearText = new Button();
        btnClearText.setLayoutX(100);
        btnClearText.setLayoutY(240);
        btnClearText.setText("Clear text window");
    }

    /**
     * Button for check hand
     */
    public void buttonCheckHand() {
        this.btnCheckHand = new Button();
        btnCheckHand.setLayoutX(300);
        btnCheckHand.setLayoutY(200);
        btnCheckHand.setText("Check hand");
    }

    /**
     * Creates a text area
     */
    public void createATextObject() {
        this.text = new TextArea();
    }

    /**
     * A method used to check for flush
     */
    public void checkForFlush() {
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

    /**
     * A method to print hearts and face
     */
    public void printHeartsAndFace() {
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

    /**
     * A method to check for queen of spades
     */
    public void getQueenOfSpades() {
        text.appendText("Queen of spades in hand: " + hand.isQueenOfSpadesInHand());
    }

    /**
     * A method to clear the text area
     */
    public void clearTextArea() {
        text.deleteText(0, text.getLength());
    }

    /**
     * This method starts the process
     * @param primaryStage, the display window
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.hand = new Hand();
        primaryStage.setTitle("Card GUI");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);

        buttonGetHand();
        buttonRefreshHand();
        buttonClearText();
        buttonCheckHand();
        createATextObject();

        /**
         * Displays the amount of cards that have been drawn when clicking on
         * the button btnGetHand
         */
        btnGetHand.setOnAction(event ->
                text.appendText("Cards drawn: " + hand.getNumberOfCardsToDraw() + "\n")
        );

        /**
         * It will create a new deck of cards
         */
        btnRefreshHand.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hand.makeANewDeck();
                text.appendText("Hand is now refreshed\n");
            }
        });

        /**
         * Checks the hand for flush, it displays hearts and face on hand, and it
         * says if queen of spades is on hand
         */
        btnCheckHand.setOnAction(event -> {
            checkForFlush();
            text.appendText("Face and suit on hand: ");
            printHeartsAndFace();
            text.appendText("Face sum on hand: " + hand.sumOfFaceOnHand() + "\n");
            getQueenOfSpades();
            text.appendText("\n\n");
        });

        btnClearText.setOnAction(event ->
                clearTextArea()
        );

        // Displays all the buttons
        root.getChildren().add(btnGetHand);
        root.getChildren().add(btnRefreshHand);
        root.getChildren().add(btnClearText);
        root.getChildren().add(btnCheckHand);
        root.getChildren().add(text);
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