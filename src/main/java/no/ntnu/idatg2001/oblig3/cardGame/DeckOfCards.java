package no.ntnu.idatg2001.oblig3.cardGame;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class represents a deck of cards. 13 cards of each
 * sort (spades, hearts, diamonds and clubs)
 *
 * @author Vegard Grøder
 * @version 1.0.0
 */
public class DeckOfCards {
    // The class' fields
    private final char[] suit = {'S', 'H', 'D', 'C'};
    private int cardNumber = 1;
    private List<PlayingCard> cardList;
    Random rand;

    /**
     * Calls createANewDeck method, which creates a new deck,
     * and initializes the global field, hand
     */
    public DeckOfCards() {
        this.rand = new Random();
        createANewDeck();
    }

    /**
     * Creates and returns a hand of cards
     *
     * @param numberOfCardsToDraw, amount of playing cards in a hand
     * @return hand, a list of playing cards
     * @throws IllegalArgumentException, if the number of cards to draw is greater
     *                                   than the number of cards left in the deck, or if the number of cards to draw is less
     *                                   than one
     */
    public Collection<PlayingCard> dealHand(int numberOfCardsToDraw) throws IllegalArgumentException {
        if (numberOfCardsToDraw > cardList.size())
            throw new IllegalArgumentException("Can not draw that number of cards");
        if (numberOfCardsToDraw < 1)
            throw new IllegalArgumentException("Can not draw 0 or less cards");

        List<PlayingCard> hand = new ArrayList<>();
        int card = 0;
        while (card < numberOfCardsToDraw) {
            int upperBound = cardList.size();
            int randomCardFromList = rand.nextInt(upperBound);
            hand.add(cardList.get(randomCardFromList));
            cardList.remove(randomCardFromList);
            card++;
        }
        return hand;
    }

    /**
     * This method creates a new deck
     */
    public void createANewDeck() {
        this.cardList = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            while (cardNumber < 14) {
                cardList.add(new PlayingCard(suit[i], cardNumber));
                cardNumber++;
            }
            cardNumber = 1;
        }
    }
}
