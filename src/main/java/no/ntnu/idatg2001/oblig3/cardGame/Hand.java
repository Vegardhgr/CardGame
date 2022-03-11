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




        /*if (checkerForFullHouse(sortedHand)) {
            return "Full house";
        }*/

        /*if (checkerForStraight(sortedHand)) {
            return "Straight";
        }
        if (checkerForThreeOfAKind(sortedHand)) {
            return "Three of a kind";
        }
        int pairs = checkerForPairs(sortedHand);

        if (pairs == 1) {
            return "One pair";
        } else if (pairs == 2) {
            return "Two pairs";
        }*/
        return null;
    }


    private boolean checkerForFlush (PlayingCard[] sortedHand) {
        if (sortedHand[0].getSuit() == sortedHand[NUMBER_OF_CARDS_DRAWN-1].getSuit()) {
            return true;
        }
        return false;
    }

    /*private boolean checkerForFullHouse (PlayingCard[] sortedHand) {
        int previousCardNumber = sortedHand[0].getFace();
        int i = 0;
        if ((previousCardNumber == sortedHand[i+1].getFace() &&
            sortedHand[i+2].getFace() == sortedHand[i+3].getFace() &&
            sortedHand[i+2].getFace() == sortedHand[i+4].getFace()) ||
            (previousCardNumber == sortedHand[i+1].getFace() &&
            sortedHand[i].getFace() == sortedHand[i+2].getFace() &&
            sortedHand[i+3].getFace() == sortedHand[i+4].getFace())) {
            return true;
        }
        return false;
    }*/



    /*private boolean checkerForStraight (PlayingCard[] sortedHand) {
        int previousCardNumber = sortedHand[0].getFace();
        for (int i = 1; i < NUMBER_OF_CARDS_DRAWN; i++) {
            if (!(previousCardNumber == sortedHand[i].getFace()+1)) {
                return false;
            }
            previousCardNumber = sortedHand[i].getFace();
        }
        return true;
    }

    private boolean checkerForThreeOfAKind(PlayingCard[] sortedHand) {
        int g = 0;
        int d = 1;
        int counterForPairs = 0;
        for (int j = 2; j < NUMBER_OF_CARDS_DRAWN; j++) {
            if (sortedHand[g].getFace() == sortedHand[d].getFace() &&
                    sortedHand[g].getFace() == sortedHand[j].getFace()){
                return true;
            }
            g++;
            d++;
        }
        return false;
    }

    private int checkerForPairs(PlayingCard[] sortedHand) {
        int g = 0;
        int counterForPairs = 0;
        for (int j = 1; j < NUMBER_OF_CARDS_DRAWN; j++) {
            if (sortedHand[g].getFace() == sortedHand[j].getFace()) {
                counterForPairs++;
            }
            g++;
        }

        return counterForPairs;
    }*/

    public static void main(String[] args) {
        Hand hand = new Hand();
        hand.checkHand();
    }
}
