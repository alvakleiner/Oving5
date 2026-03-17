package edu.ntnu.idatt2003.cardgame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Unit tests for the {@link DeckOfCards} class.
 * <p>
 *   This test class verifies that a deck of cards is correctly constructed
 *   and that its methods return the expected values.
 * </p>
 * <p>
 *   All tests follow the AAA pattern.
 * </p>
 */
class DeckOfCardsTest {

    private DeckOfCards deck;

    @BeforeEach
    void setUp() {
        deck = new DeckOfCards();
    }

    @Nested
    @DisplayName("DeckOfCards()")
    class Constructor {

        @Test
        @DisplayName("Should contain 52 cards when created")
        void contains52Cards() {
            // Act & Assert
            assertEquals(52, deck.getDeck().size());
        }

        @Test
        @DisplayName("Should contain all four suits")
        void containsAllFourSuits() {
            // Act
            long spades = deck.getDeck().stream().filter(c -> c.getSuit() == 'S').count();
            long hearts = deck.getDeck().stream().filter(c -> c.getSuit() == 'H').count();
            long diamonds = deck.getDeck().stream().filter(c -> c.getSuit() == 'D').count();
            long clubs = deck.getDeck().stream().filter(c -> c.getSuit() == 'C').count();
            // Assert
            assertEquals(13, spades);
            assertEquals(13, hearts);
            assertEquals(13, diamonds);
            assertEquals(13, clubs);
        }

        @Test
        @DisplayName("Should contain face values 1 through 13 for each suit")
        void containsAllFaceValues() {
            // Act
            long hearts = deck.getDeck().stream()
                    .filter(c -> c.getSuit() == 'H')
                    .map(PlayingCard::getFace)
                    .distinct()
                    .count();
            // Assert
            assertEquals(13, hearts);
        }
    }

    @Nested
    @DisplayName("getDeck()")
    class GetDeck {

        @Test
        @DisplayName("Should return a copy of the deck")
        void returnsCopyOfDeck() {
            // Act
            List<PlayingCard> copy = deck.getDeck();
            copy.clear();
            // Assert
            assertEquals(52, deck.getDeck().size());
        }
    }

    @Nested
    @DisplayName("dealHand()")
    class DealHand {

        @Test
        @DisplayName("Should return correct number of cards")
        void returnsCorrectNumberOfCards() {
            // Act
            HandOfCards hand = deck.dealHand(5);
            // Assert
            assertEquals(5, hand.getCards().size());
        }

        @Test
        @DisplayName("Should remove dealt cards from deck")
        void removesDealtCardsFromDeck() {
            // Act
            deck.dealHand(5);
            // Assert
            assertEquals(47, deck.getDeck().size());
        }

        @Test
        @DisplayName("Should throw exception when n is less than 1")
        void throwsExceptionWhenNIsLessThan1() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () ->
                    deck.dealHand(0));
        }

        @Test
        @DisplayName("Should throw exception when n is greater than 52")
        void throwsExceptionWhenNIsGreaterThan52() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () ->
                    deck.dealHand(53));
        }

        @Test
        @DisplayName("Should allow dealing all 52 cards")
        void allowsDealingAll52Cards() {
            // Act
            HandOfCards hand = deck.dealHand(52);
            // Assert
            assertEquals(52, hand.getCards().size());
            assertEquals(0, deck.getDeck().size());
        }
    }
}