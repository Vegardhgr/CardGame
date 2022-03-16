package no.ntnu.idatg2001.oblig3.cardGame.gui;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import no.ntnu.idatg2001.oblig3.cardGame.Hand;
import no.ntnu.idatg2001.oblig3.cardGame.PlayingCard;

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
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView[] imageViewArr;
    TranslateTransition transition;
    ScaleTransition scale;

    // An array of all possible card faces
    private String[] faceList = new String[]{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "faceDownCard"};

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
        this.btnGetHand.setLayoutY(220);
        this.btnGetHand.setText("Deal hand");
    }

    /**
     * Button to get a new deck of cards
     */
    public void buttonRefreshHand() {
        this.btnRefreshHand = new Button();
        this.btnRefreshHand.setLayoutX(500);
        this.btnRefreshHand.setLayoutY(320);
        this.btnRefreshHand.setText("Refresh hand");
    }

    /**
     * Face down cards
     * @param index, the index to an image in imageViewArr
     */
    public void faceDownCards(int index) {
        int i = index;
        TranslateTransition translateTransition = new TranslateTransition();
        imageViewArr[i].setImage(new Image("file:src/images/faceDownCard.png"));
        translateTransition.setNode(imageViewArr[i]);
        translateTransition.setByX(20);
        translateTransition.setDuration(Duration.millis(100));
        TranslateTransition translateTransition2 = new TranslateTransition();
        translateTransition.setOnFinished(p -> {
            translateTransition2.setNode(imageViewArr[i]);
            translateTransition2.setByX(-20);
            translateTransition2.setDuration(Duration.millis(100));
            translateTransition2.play();

        });
        translateTransition.play();

    }

    /**
     * Button to clear the text window
     */
    public void buttonClearText() {
        this.btnClearText = new Button();
        this.btnClearText.setLayoutX(350);
        this.btnClearText.setLayoutY(320);
        this.btnClearText.setText("Clear text window");
    }

    /**
     * An array of all the images
     */
    public void imageViewArr() {
        this.imageViewArr = new ImageView[]{imageView1, imageView2, imageView3, imageView4, imageView5};
    }

    /**
     * Button for check hand
     */
    public void buttonCheckHand() {
        this.btnCheckHand = new Button();
        this.btnCheckHand.setLayoutX(500);
        this.btnCheckHand.setLayoutY(220);
        this.btnCheckHand.setText("Check hand");
    }

    /**
     * Creates a text area
     */
    public void createATextObject() {
        this.text = new TextArea();
        this.text.setLayoutX(20);
        this.text.setMaxHeight(100);
        this.text.setMaxWidth(310);
    }

    /**
     * Placeholder for image 1
     */
    public void imageView1() {
        this.imageView1 = new ImageView();
        this.imageView1.setLayoutX(10);
        this.imageView1.setLayoutY(110);
        this.imageView1.setFitHeight(75);
        this.imageView1.setFitWidth(75);
    }

    /**
     * Placeholder for image 2
     */
    public void imageView2() {
        this.imageView2 = new ImageView();
        this.imageView2.setLayoutX(85);
        this.imageView2.setLayoutY(110);
        this.imageView2.setFitHeight(75);
        this.imageView2.setFitWidth(75);
    }

    /**
     * Placeholder for image 3
     */
    public void imageView3() {
        this.imageView3 = new ImageView();
        this.imageView3.setLayoutX(160);
        this.imageView3.setLayoutY(110);
        this.imageView3.setFitHeight(75);
        this.imageView3.setFitWidth(75);
    }

    /**
     * Placeholder for image 4
     */
    public void imageView4() {
        this.imageView4 = new ImageView();
        this.imageView4.setLayoutX(235);
        this.imageView4.setLayoutY(110);
        this.imageView4.setFitHeight(75);
        this.imageView4.setFitWidth(75);
    }

    /**
     * Placeholder for image 5
     */
    public void imageView5() {
        this.imageView5 = new ImageView();
        this.imageView5.setLayoutX(310);
        this.imageView5.setLayoutY(110);
        this.imageView5.setFitHeight(75);
        this.imageView5.setFitWidth(75);
    }

    /**
     * Calls set transition for all the images in imageViewArr
     */
    public void loadAllTransitions() {
        for (int i = 0; i < 5; i++)
            setTransition(i);
    }

    /**
     * Sets the transition for all the cards in imageViewArr
     * @param index, index for an image in imageViewArr
     */
    public void setTransition(int index) {
        int i = index;
        this.transition = new TranslateTransition();
        this.scale = new ScaleTransition();
        transition.setDuration(Duration.millis(100));
        transition.setNode(imageViewArr[i]);
        transition.setByY(0);
        transition.setAutoReverse(true);
        transition.setCycleCount(6);
        scale.setNode(imageViewArr[i]);
        scale.setByX(1);
        scale.setDuration(Duration.millis(1000));
        scale.setByX(-1);
        ScaleTransition scale2 = new ScaleTransition();
        scale.setOnFinished(p -> {
            loadImages();
            scale2.setNode(imageViewArr[i]);
            scale2.setByX(1);
            scale2.setDuration(Duration.millis(1000));
            scale2.play();
        });
        scale2.setOnFinished(p -> {
            btnGetHand.setDisable(false);
            btnRefreshHand.setDisable(false);
            btnCheckHand.setDisable(false);
        });
        scale.play();
        transition.play();
        scale.play();
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
     * Creates a label and a text field for flush checker
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
        } catch (IllegalArgumentException e) {
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
     *
     * @param primaryStage, the display window
     */
    @Override
    public void start(Stage primaryStage) {
        this.hand = new Hand();
        primaryStage.setTitle("Card GUI");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);

        // Method calls to create the layout
        buttonGetHand();
        buttonRefreshHand();
        buttonClearText();
        buttonCheckHand();
        imageView1();
        imageView2();
        imageView3();
        imageView4();
        imageView5();
        imageViewArr();
        createATextObject();
        textQueenOfSpades();
        textHeartsAndFace();
        textFlush();
        sumOnHand();
        btnCheckHand.setDisable(true);
        btnRefreshHand.setDisable(true);

        for (int i = 0; i < 5; i++)
            faceDownCards(i);

        /**
         * Displays the amount of cards that have been drawn, what cards you have drawn
         * and it will display an image of all the cards
         */
        btnGetHand.setOnAction(event -> {
            btnGetHand.setDisable(true);
            btnCheckHand.setDisable(true);
            btnRefreshHand.setDisable(true);
            try {
                hand.makeHand();
                text.appendText("Cards drawn: " + hand.getNumberOfCardsToDraw() + "\n");
                text.appendText("Your hand: ");
                hand.getHand().forEach(p -> text.appendText(p.getSuit() + "" + p.getFace() + " "));
                text.appendText("\n");

                loadAllTransitions();
                text.appendText("\n");
            } catch (IllegalArgumentException e) {
                text.appendText("\n" + e.getMessage() + ".\nPlease refresh hand\n");
            }
        });

        /**
         * Creates a new deck of cards and gives
         * feedback to user
         */
        btnRefreshHand.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btnRefreshHand.setDisable(true);
                btnCheckHand.setDisable(true);
                hand.makeANewDeck();
                for (int i = 0; i < 5; i++)
                    faceDownCards(i);
                text.appendText("Hand is now refreshed\n");
                btnGetHand.setDisable(false);
            }
        });

        /**
         * Checks the hand for flush, it displays hearts and face on hand, and it
         * says if queen of spades is on hand
         */
        btnCheckHand.setOnAction(event -> {
            btnCheckHand.setDisable(true);
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
        root.getChildren().addAll(
                btnGetHand,
                btnRefreshHand,
                btnClearText,
                btnCheckHand,
                imageView1,
                imageView2,
                imageView3,
                imageView4,
                imageView5,
                text,
                textFieldQueenOfSpades,
                queenFieldLabel,
                heartsAndFaceLabel,
                textFieldHeartsAndFace,
                flushLabel,
                textFieldFlush,
                sumOnHandLabel,
                textFieldSum);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Puts new images in imageViewArr
     */
    private void loadImages() {
        int i = 0;
        for (PlayingCard card : hand.getHand()) {
            if (card.getSuit() == 'H') {
                imageViewArr[i].setImage(new Image("file:src/images/" + faceList[card.getFace() - 1] + "_of_hearts.png"));
            } else if (card.getSuit() == 'D') {
                imageViewArr[i].setImage(new Image("file:src/images/" + faceList[card.getFace() - 1] + "_of_diamonds.png"));
            } else if (card.getSuit() == 'C') {
                imageViewArr[i].setImage(new Image("file:src/images/" + faceList[card.getFace() - 1] + "_of_clubs.png"));
            } else {
                imageViewArr[i].setImage(new Image("file:src/images/" + faceList[card.getFace() - 1] + "_of_spades.png"));
            }
            i++;
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}