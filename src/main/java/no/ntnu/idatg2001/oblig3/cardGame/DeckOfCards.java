package no.ntnu.idatg2001.oblig3.cardGame;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a deck of cards. 13 cards of each
 * sort (spades, hearts, diamonds and clubs)
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
}
