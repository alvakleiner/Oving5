package edu.ntnu.idatt2003.cardgame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link PlayingCard} class.
 * <p>
 *   This test class verifies that a playing card is correctly constructed
 *   and that its methods return the expected values.
 * </p>
 * <p>
 *   All tests follow the AAA pattern.
 * </p>
 */
class PlayingCardTest {

    private final PlayingCard card = new PlayingCard('H', 4);

    @Nested
    @DisplayName("PlayingCard()")
    class Constructor {

        @Test
        @DisplayName("Should return correct suit when valid card created")
        void returnsCorrectSuit() {
            // Act & Assert
            assertEquals('H', card.getSuit());
        }

        @Test
        @DisplayName("Should return correct face when valid card created")
        void returnsCorrectFace() {
            // Act & Assert
            assertEquals(4, card.getFace());
        }

        @Test
        @DisplayName("Should throw exception when suit is invalid")
        void throwsExceptionWhenSuitIsInvalid() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () ->
                    new PlayingCard('X', 4));
        }

        @Test
        @DisplayName("Should throw exception when face is less than 1")
        void throwsExceptionWhenFaceIsLessThanOne() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () ->
                    new PlayingCard('H', 0));
        }

        @Test
        @DisplayName("Should throw exception when face is greater than 13")
        void throwsExceptionWhenFaceIsGreaterThan13() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () ->
                    new PlayingCard('H', 14));
        }

        @Test
        @DisplayName("Should accept all valid suits")
        void acceptsAllValidSuits() {
            // Act & Assert
            assertDoesNotThrow(() -> new PlayingCard('H', 1));
            assertDoesNotThrow(() -> new PlayingCard('D', 1));
            assertDoesNotThrow(() -> new PlayingCard('C', 1));
            assertDoesNotThrow(() -> new PlayingCard('S', 1));
        }

        @Test
        @DisplayName("Should accept face value 1 (Ace)")
        void acceptsFaceValueOne() {
            // Act & Assert
            assertDoesNotThrow(() -> new PlayingCard('H', 1));
        }

        @Test
        @DisplayName("Should accept face value 13 (King)")
        void acceptsFaceValueThirteen() {
            // Act & Assert
            assertDoesNotThrow(() -> new PlayingCard('H', 13));
        }
    }

    @Nested
    @DisplayName("getAsString()")
    class GetAsString {

        @Test
        @DisplayName("Should return correct string representation")
        void returnsCorrectString() {
            // Act & Assert
            assertEquals("H4", card.getAsString());
        }
    }

    @Nested
    @DisplayName("equals()")
    class Equals {

        @Test
        @DisplayName("Should return true when cards are equal")
        void returnsTrueWhenCardsAreEqual() {
            // Arrange
            PlayingCard other = new PlayingCard('H', 4);
            // Act & Assert
            assertEquals(card, other);
        }

        @Test
        @DisplayName("Should return false when suits differ")
        void returnsFalseWhenSuitsDiffer() {
            // Arrange
            PlayingCard other = new PlayingCard('D', 4);
            // Act & Assert
            assertNotEquals(card, other);
        }

        @Test
        @DisplayName("Should return false when faces differ")
        void returnsFalseWhenFacesDiffer() {
            // Arrange
            PlayingCard other = new PlayingCard('H', 5);
            // Act & Assert
            assertNotEquals(card, other);
        }

        @Test
        @DisplayName("Should return true when compared to itself")
        void returnsTrueWhenComparedToItself() {
            // Act & Assert
            //noinspection EqualsWithItself
            assertEquals(card, card);
        }

        @Test
        @DisplayName("Should return false when compared to null")
        void returnsFalseWhenComparedToNull() {
            // Act & Assert
            assertNotEquals(null, card);
        }
    }
}