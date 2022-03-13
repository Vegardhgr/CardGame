package no.ntnu.idatg2001.oblig3.cardGame;

import java.util.*;

/**
 * This class represents the cards on hand
 *
 * @author Vegard Grøder
 * @version 1.0.0
 */
public class Hand {
    // Private global variables
    private DeckOfCards cards;
    private final static int NUMBER_OF_CARDS_DRAWN = 5;

    /**
     * This is constructor initializes the class' field
     */
    public Hand() {
        this.cards = new DeckOfCards();
    }

    private List<PlayingCard> makeHand() throws IllegalArgumentException{
        return new ArrayList<>(cards.dealHand(NUMBER_OF_CARDS_DRAWN));
    }

    /**
     * This method checks if a hand is flush or not
     *
     * @return String, if the hand is flush it returns Flush. If not,
     * it returns an empty string
     */
    public String checkHand() throws IllegalArgumentException {
        List<PlayingCard> hand = new ArrayList<>();
        hand = makeHand();
        PlayingCard[] sortedHand = new PlayingCard[NUMBER_OF_CARDS_DRAWN];
        hand.sort(PlayingCard::compareTo);
        int i = 0;
        for (PlayingCard card : hand) {
            sortedHand[i] = card;
            i++;
        }
        if (checkerForFlush(sortedHand)) {
            return "Flush";
        }

        return "";
    }

    /**
     * Checks if the cards that you have on hand is a flush
     * @param sortedHand, an array of the cards on hand in sorted
     *                    order
     * @return boolean, true if it is a flush
     */
    private boolean checkerForFlush(PlayingCard[] sortedHand) {
        if (sortedHand[0].getSuit() == sortedHand[NUMBER_OF_CARDS_DRAWN - 1].getSuit()) {
            return true;
        }
        return false;
    }

    /**
     * Returns the sum of all the cards on hand
     * @return int, sum of all the cards
     */
    public int sumOfFaceOnHand() {
        return cards.getCardListFace().sum();
    }

    /**
     * This method creates a new deck
     */
    public void makeANewDeck() {
        cards.createANewDeck();
    }
}
