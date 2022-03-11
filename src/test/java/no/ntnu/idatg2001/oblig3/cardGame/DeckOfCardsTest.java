package no.ntnu.idatg2001.oblig3.cardGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class represents a deck of cards
 *
 * @author Vegard Grøder
 * @version 1.0.0
 */
class DeckOfCardsTest {
    // Private global variables
    private final char[] suit = {'S', 'H', 'D', 'C'};
    private int cardNumber = 1;
    private List<PlayingCard> cardList;
    private Random rand;
    private int amountOfCardsToDraw;

    @BeforeEach
    void setUp() {
        this.cardList = new ArrayList<>();
        this.rand = new Random();
    }

    /**
     * This method tests if a deck of cards is created
     */
    @Test
    void makeADeckOfCards() {
        assertEquals(0, cardList.size());
        for (int i = 0; i <= 3; i++) {
            while (cardNumber < 14) {
                cardList.add(new PlayingCard(suit[i], cardNumber));
                cardNumber++;
            }
            cardNumber = 1;
        }
        assertEquals(52, cardList.size());
    }

    /**
     * Tests that dealHand deals the amount of cards that the variable
     * amountOfCardsToDraw is set to
     */
    @Test
    void dealHand() {
        List<PlayingCard> cardsOnHand = new ArrayList<>();
        int numberOfCardsDrawn = 0;
        makeADeckOfCards();
        this.amountOfCardsToDraw = 5;
        assertEquals(0, cardsOnHand.size());
        while (numberOfCardsDrawn < amountOfCardsToDraw) {
            int cardIndex = rand.nextInt(cardList.size());
            cardsOnHand.add(cardList.get(cardIndex));
            cardList.remove(cardIndex);
            numberOfCardsDrawn++;
            assertEquals(52-numberOfCardsDrawn, cardList.size());
            assertEquals(numberOfCardsDrawn, cardsOnHand.size());
        }
        assertEquals(amountOfCardsToDraw, cardsOnHand.size());
    }

}