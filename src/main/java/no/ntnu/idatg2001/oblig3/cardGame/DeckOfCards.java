package no.ntnu.idatg2001.oblig3.cardGame;

import java.util.*;

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

    /**
     * Making the whole deck
     */
    public DeckOfCards() {
        this.cardList = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            while (cardNumber < 14) {
                cardList.add(new PlayingCard(suit[i], cardNumber));
                cardNumber++;
            }
            cardNumber = 1;
        }
    }

    /**
     * Creates and returns a hand of cards
     * @param numberOfCardsToDraw, amount of playing cards in a hand
     * @return hand, a list of playing cards
     */
    public Collection<PlayingCard> dealHand(int numberOfCardsToDraw) {
        List<PlayingCard> hand = new ArrayList<>();
        int card = 0;
        while (card < numberOfCardsToDraw) {
            Random rand = new Random();
            int upperBound = cardList.size();
            int randomCardFromList = rand.nextInt(upperBound);
            hand.add(cardList.get(randomCardFromList));
            cardList.remove(randomCardFromList);
            card++;
        }
        return hand;
    }
}
