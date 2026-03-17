package edu.ntnu.idatt2003.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HandOfCards {
    private final List<PlayingCard> cards;

    public HandOfCards(List<PlayingCard> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<PlayingCard> getCards() {
        return new ArrayList<>(cards);
    }

    public int getSumOfFaces() {
        return cards.stream().mapToInt(PlayingCard::getFace).sum();
    }

    public List<PlayingCard> getHearts() {
        return cards.stream().filter(card -> card.getSuit() == 'H').toList();
    }

    public boolean hasQueenOfSpades() {
        return cards.stream().anyMatch(card -> card.getSuit() == 'S' && card.getFace() == 12);
    }

    public boolean hasFlush() {
        return cards.stream()
                .collect(Collectors.groupingBy(PlayingCard::getSuit, Collectors.counting()))
                .values().stream()
                .anyMatch(count -> count >= 5);
    }
}
