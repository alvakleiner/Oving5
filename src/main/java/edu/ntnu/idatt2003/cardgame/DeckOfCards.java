package edu.ntnu.idatt2003.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {
    private final List<PlayingCard> deck;

    public DeckOfCards() {
        deck = new ArrayList<>();
        char[] suit = { 'S', 'H', 'D', 'C' };

        for (char s : suit) {
            for (int f = 1; f <= 13; f++) {
                deck.add(new PlayingCard(s, f));
            }
        }
    }

    public List<PlayingCard> getDeck() {
        return new ArrayList<>(deck);
    }

    public HandOfCards dealHand(int n) {
        if (n < 1 || n > 52) {
            throw new IllegalArgumentException("Number of cards must be between 1 and 52.");
        }

        Collections.shuffle(deck);
        HandOfCards hand = new HandOfCards(deck.subList(0, n));
        deck.subList(0, n).clear();

        return hand;
    }
}
