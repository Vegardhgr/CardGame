package no.ntnu.idatg2001.oblig3.cardGame;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class represents the cards on hand
 *
 * @author Vegard Grøder
 * @version 1.0.0
 */
public class Hand {
    // Private global variables
    private DeckOfCards cards;
    private List<PlayingCard> hand;
    private final static int NUMBER_OF_CARDS_DRAWN = 5;

    /**
     * This is constructor initializes the class' field
     */
    public Hand() {
        this.hand = new ArrayList<>();
        this.cards = new DeckOfCards();
    }

    public void makeHand() throws IllegalArgumentException{
         this.hand = new ArrayList<>(cards.dealHand(NUMBER_OF_CARDS_DRAWN));
    }

    public List<PlayingCard> returnHand() throws IllegalArgumentException{
        return new ArrayList<>(cards.dealHand(NUMBER_OF_CARDS_DRAWN));
    }

    /**
     * This method checks if a hand is flush or not
     *
     * @return String, if the hand is flush it returns Flush. If not,
     * it returns an empty string
     */
    public String checkHand() throws IllegalArgumentException {
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
     * This method creates a new deck
     */
    public void makeANewDeck() {
        cards.createANewDeck();
    }

    /**
     * Returns the number of cards to draw
     * @return NUMBER_OF_CARDS_DRAWN
     */
    public int getNumberOfCardsToDraw() {
        return NUMBER_OF_CARDS_DRAWN;
    }

    /**
     * This method returns a stream of the numbers on the cards
     * @return IntStream, a strem of the numbers on the cards
     */
    public int sumOfFaceOnHand() {
        return this.hand
                .stream()
                .mapToInt(p -> p.getFace()).sum();
    }

    /**
     * Returns a list with all the cards that are hearts, in hand
     * @return List<PlayingCard>
     */
    public List<PlayingCard> getHeartsFromHand() {
        return this.hand
                .stream()
                .filter(p -> p.getSuit() == 'H')
                .collect(Collectors.toList());
    }

    /**
     * Checks if queen of spades is in hand
     * @return boolean, true if queen of spades is in hand
     */
    public boolean isQueenOfSpadesInHand() {
        return hand
                .stream()
                .anyMatch(p -> p.getSuit() == 'H' && p.getFace() == 12);
    }
}
