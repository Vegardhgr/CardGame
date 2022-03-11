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

    public List<PlayingCard> makeHand() {
        return new ArrayList<>(cards.dealHand(NUMBER_OF_CARDS_DRAWN));
    }

    /**
     * This method checks if a hand is flush or not
     * @return String, if the hand is flush it returns Flush. If not,
     * it returns null
     */
    private String checkHand() {
        List<PlayingCard> hand = makeHand();
        try {
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
        } catch (NegativeArraySizeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    private boolean checkerForFlush (PlayingCard[] sortedHand) {
        if (sortedHand[0].getSuit() == sortedHand[NUMBER_OF_CARDS_DRAWN-1].getSuit()) {
            return true;
        }
        return false;
    }
}
