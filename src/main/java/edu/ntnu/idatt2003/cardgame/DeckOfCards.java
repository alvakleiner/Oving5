package edu.ntnu.idatt2003.cardgame;

import java.util.ArrayList;
import java.util.List;

public class DeckOfCards {
    private final List<PlayingCard> deck;
    private final char[] suit = { 'S', 'H', 'D', 'C' };

    public DeckOfCards() {
        this.deck = new ArrayList<>();

        for (char s : suit) {
            for (int f = 1; f <= 13; f++) {
                this.deck.add(new PlayingCard(s, f));
            }
        }
    }

    public List<PlayingCard> getDeck() {
        return this.deck;
    }
}
