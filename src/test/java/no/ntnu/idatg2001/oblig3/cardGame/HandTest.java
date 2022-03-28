package no.ntnu.idatg2001.oblig3.cardGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the class Hand
 */
class HandTest {
    // Global variables
    Hand hand;
    List<PlayingCard> cardList;
    Random rand;

    @BeforeEach
    void setUp() {
        this.hand = new Hand();
        this.cardList = new ArrayList<>();
        this.rand = new Random();
    }

    /**
     * A method that creates a hand which contains a flush
     *
     * @return array with type PlayingCard
     */
    private PlayingCard[] getFlush() {
        PlayingCard[] flush = new PlayingCard[5];
        List<PlayingCard> thirteenCardsOfHearts = new ArrayList<>();
        int cardsAdded = 0;
        while (cardsAdded < 13) {
            cardsAdded++;
            thirteenCardsOfHearts.add(new PlayingCard('H', cardsAdded));
        }
        for (int i = 0; i < 5; i++) {
            int cardDrawnIndex = rand.nextInt(thirteenCardsOfHearts.size());
            int playingCardNumber = thirteenCardsOfHearts.get(cardDrawnIndex).getFace();
            flush[i] = new PlayingCard('H', playingCardNumber);
            thirteenCardsOfHearts.remove(cardDrawnIndex);
        }
        return flush;
    }

    /**
     * A method that creates a hand which does not contain
     * a flush
     *
     * @return array with type PlayingCard
     */
    private PlayingCard[] getNoFlush() {
        PlayingCard[] notFlush = new PlayingCard[5];
        List<PlayingCard> thirteenCardsOfHearts = new ArrayList<>();
        int cardsAdded = 0;
        while (cardsAdded < 13) {
            cardsAdded++;
            thirteenCardsOfHearts.add(new PlayingCard('H', cardsAdded));
        }
        for (int i = 0; i < 5; i++) {
            int cardDrawnIndex = rand.nextInt(thirteenCardsOfHearts.size());
            int playingCardNumber = thirteenCardsOfHearts.get(cardDrawnIndex).getFace();
            if (i == 0) {
                notFlush[i] = new PlayingCard('H', playingCardNumber);
            } else {
                notFlush[i] = new PlayingCard('D', playingCardNumber);
            }
            thirteenCardsOfHearts.remove(cardDrawnIndex);
        }
        return notFlush;
    }

    /**
     * A test method for the methods getFlush and getNoFlush
     * that checks if a hand contains a flush or not
     */
    @Test
    void checkHand() {
        boolean handIsFlush = true;
        PlayingCard[] cardsOnHand = getFlush();
        for (PlayingCard card : cardsOnHand) {
            if (card.getSuit() != 'H') {
                assertFalse(handIsFlush);
            }
        }
        assertTrue(handIsFlush);

        boolean handIsNotFlush = false;
        PlayingCard[] cardsOnHand2 = getNoFlush();
        char previousCardSuit = ' ';
        for (PlayingCard card : cardsOnHand2) {
            if (previousCardSuit != ' ' && !(card.getSuit() == previousCardSuit)) {
                handIsNotFlush = true;
                assertTrue(handIsNotFlush);
            }
            previousCardSuit = card.getSuit();
        }
        if (!handIsNotFlush) {
            assertFalse(!handIsNotFlush);
        }
    }

    private List<PlayingCard> getListWithQueenOfSpades() {
        List<PlayingCard> cardList = new ArrayList<>();
        cardList.add(new PlayingCard('S', 12));
        cardList.add(new PlayingCard('H', 12));
        cardList.add(new PlayingCard('D', 10));
        return cardList;
    }

    private List<PlayingCard> getListWithoutQueenOfSpades() {
        List<PlayingCard> cardList = new ArrayList<>();
        cardList.add(new PlayingCard('C', 12));
        cardList.add(new PlayingCard('H', 12));
        cardList.add(new PlayingCard('D', 10));
        return cardList;
    }

    @Test
    void checkForQueenOfSpadesOnHand() {
        assertTrue(getListWithQueenOfSpades()
                .stream()
                .anyMatch(p -> p.getSuit() == 'S' && p.getFace() == 12));
        assertFalse(getListWithoutQueenOfSpades()
                .stream()
                .anyMatch(p -> p.getSuit() == 'S' && p.getFace() == 12));
    }

    private List<PlayingCard> getCards() {
        List<PlayingCard> cardList = new ArrayList<>();
        cardList.add(new PlayingCard('H',10));
        cardList.add(new PlayingCard('S', 13));
        cardList.add(new PlayingCard('H',2));
        cardList.add(new PlayingCard('D', 6));
        cardList.add(new PlayingCard('H',2));
        return cardList;
    }

    @Test
    void checkHeartOnHand() {
        assertEquals(3, this.getCards()
                .stream()
                .filter(p -> p.getSuit() == 'H')
                .collect(Collectors.toList()).size());
    }
}