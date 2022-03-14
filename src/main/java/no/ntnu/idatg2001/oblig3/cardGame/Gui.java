package no.ntnu.idatg2001.oblig3.cardGame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    Label queenFieldLabel;
    TextField textFieldQueenOfSpades;

    Label heartsAndFaceLabel;
    TextField textFieldHeartsAndFace;

    Label sumOnHandLabel;
    TextField textFieldSum;

    Label flushLabel;
    TextField textFieldFlush;

    /**
     * Button for get hand
     */
    public void buttonGetHand() {
        this.btnGetHand = new Button();
        this.btnGetHand.setLayoutX(350);
        this.btnGetHand.setLayoutY(50);
        this.btnGetHand.setText("Get hand");
    }

    /**
     * Button to get a new deck of cards
     */
    public void buttonRefreshHand() {
        this.btnRefreshHand = new Button();
        this.btnRefreshHand.setLayoutX(500);
        this.btnRefreshHand.setLayoutY(120);
        this.btnRefreshHand.setText("Refresh hand");
    }

    /**
     * Button to clear the text window
     */
    public void buttonClearText() {
        this.btnClearText = new Button();
        this.btnClearText.setLayoutX(350);
        this.btnClearText.setLayoutY(120);
        this.btnClearText.setText("Clear text window");
    }

    /**
     * Button for check hand
     */
    public void buttonCheckHand() {
        this.btnCheckHand = new Button();
        this.btnCheckHand.setLayoutX(500);
        this.btnCheckHand.setLayoutY(50);
        this.btnCheckHand.setText("Check hand");
    }

    /**
     * Creates a text area
     */
    public void createATextObject() {
        this.text = new TextArea();
        this.text.setLayoutX(20);
        this.text.setMaxHeight(200);
        this.text.setMaxWidth(310);
    }

    /**
     * Creates a label and a text field for queen of spades
     */
    public void textQueenOfSpades() {
        this.queenFieldLabel = new Label("Queen of spades");
        this.queenFieldLabel.setLayoutX(20);
        this.queenFieldLabel.setLayoutY(200);
        this.textFieldQueenOfSpades = new TextField();
        this.textFieldQueenOfSpades.setLayoutX(20);
        this.textFieldQueenOfSpades.setLayoutY(220);
    }

    /**
     * Creates a label and a text field for heart cards
     */
    public void textHeartsAndFace() {
        this.heartsAndFaceLabel = new Label("Heart cards");
        this.heartsAndFaceLabel.setLayoutX(180);
        this.heartsAndFaceLabel.setLayoutY(200);
        this.textFieldHeartsAndFace = new TextField();
        this.textFieldHeartsAndFace.setLayoutX(180);
        this.textFieldHeartsAndFace.setLayoutY(220);
    }

    /**
     * Creates a label and a text field for flush
     */
    public void textFlush() {
        this.flushLabel = new Label("Flush");
        this.flushLabel.setLayoutX(20);
        this.flushLabel.setLayoutY(300);
        this.textFieldFlush = new TextField();
        this.textFieldFlush.setLayoutX(20);
        this.textFieldFlush.setLayoutY(320);
    }

    /**
     * Creates a label and a text field for the total sum of the
     * face on the cards
     */
    public void sumOnHand() {
        this.sumOnHandLabel = new Label("Sum on hand");
        this.sumOnHandLabel.setLayoutX(180);
        this.sumOnHandLabel.setLayoutY(300);
        this.textFieldSum = new TextField();
        this.textFieldSum.setLayoutX(180);
        this.textFieldSum.setLayoutY(320);
    }

    /**
     * A method used to check for flush
     */
    public void checkForFlush() {
        try {
            String string = hand.checkHand();
            if (!(string == "")) {
                textFieldFlush.setText(string);
            } else {
                textFieldFlush.setText("Not flush");
            }
        }
        catch (IllegalArgumentException e){
            textFieldFlush.setText(e.getMessage());
        }
    }

    /**
     * A method to print hearts and face
     */
    public void printHeartsAndFace() {
        List<PlayingCard> listOfHearts = hand.getHeartsFromHand();
        textFieldHeartsAndFace.setText("");
        if (!listOfHearts.isEmpty()) {
            listOfHearts
                    .forEach(p ->
                        textFieldHeartsAndFace.appendText(p.getSuit() + "" + p.getFace() + " ")
                    );
        } else {
            textFieldHeartsAndFace.setText("No hearts");
        }
    }

    /**
     * A method to check for queen of spades
     */
    public void getQueenOfSpades() {
        textFieldQueenOfSpades.setText("" + hand.isQueenOfSpadesInHand());
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
        textQueenOfSpades();
        textHeartsAndFace();
        textFlush();
        sumOnHand();

        /**
         * Displays the amount of cards that have been drawn when clicking on
         * the button btnGetHand
         */
        btnGetHand.setOnAction(event -> {
            try {
                hand.makeHand();
                text.appendText("Cards drawn: " + hand.getNumberOfCardsToDraw() + "\n");
                text.appendText("Your hand: ");
                hand.getHand().forEach(p -> text.appendText(p.getSuit() + "" + p.getFace() + " "));
                text.appendText("\n");
            } catch (IllegalArgumentException e) {
                text.appendText("\n" + e.getMessage() + ".\nPlease refresh hand");
            }
        });

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
            printHeartsAndFace();
            textFieldSum.setText("" + hand.sumOfFaceOnHand());
            getQueenOfSpades();
        });

        /**
         * Clears the text area
         */
        btnClearText.setOnAction(event ->
                clearTextArea()
        );

        // Displays all the buttons
        root.getChildren().add(btnGetHand);
        root.getChildren().add(btnRefreshHand);
        root.getChildren().add(btnClearText);
        root.getChildren().add(btnCheckHand);
        root.getChildren().add(text);
        root.getChildren().add(textFieldQueenOfSpades);
        root.getChildren().add(queenFieldLabel);
        root.getChildren().add(heartsAndFaceLabel);
        root.getChildren().add(textFieldHeartsAndFace);
        root.getChildren().add(flushLabel);
        root.getChildren().add(textFieldFlush);
        root.getChildren().add(sumOnHandLabel);
        root.getChildren().add(textFieldSum);
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