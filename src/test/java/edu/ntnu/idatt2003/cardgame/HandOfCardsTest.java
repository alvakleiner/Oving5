package edu.ntnu.idatt2003.cardgame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link HandOfCards} class.
 * <p>
 *   This test class verifies the behaviour of the HandOfCards model,
 *   including card analysis methods.
 * </p>
 * <p>
 *   All tests follow the AAA pattern.
 * </p>
 */
class HandOfCardsTest {

    @Nested
    @DisplayName("HandOfCards()")
    class Constructor {

        @Test
        @DisplayName("Should throw exception when cards list is null")
        void throwsExceptionWhenCardsIsNull() {
            // Act & Assert
            assertThrows(NullPointerException.class, () ->
                    new HandOfCards(null));
        }
    }

    @Nested
    @DisplayName("getCards()")
    class GetCards {

        @Test
        @DisplayName("Should return a copy of the cards list")
        void returnsCopyOfCards() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of(
                    new PlayingCard('H', 1),
                    new PlayingCard('H', 2)
            ));
            // Act
            List<PlayingCard> cards = hand.getCards();
            cards.clear();
            // Assert
            assertEquals(2, hand.getCards().size());
        }
    }

    @Nested
    @DisplayName("getSumOfFaces()")
    class GetSumOfFaces {

        @Test
        @DisplayName("Should return correct sum of face values")
        void returnsCorrectSum() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of(
                    new PlayingCard('H', 1),
                    new PlayingCard('H', 2),
                    new PlayingCard('H', 3)
            ));
            // Act & Assert
            assertEquals(6, hand.getSumOfFaces());
        }

        @Test
        @DisplayName("Should return zero when hand is empty")
        void returnsZeroWhenHandIsEmpty() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of());
            // Act & Assert
            assertEquals(0, hand.getSumOfFaces());
        }
    }

    @Nested
    @DisplayName("getHearts()")
    class GetHearts {

        @Test
        @DisplayName("Should return only hearts")
        void returnsOnlyHearts() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of(
                    new PlayingCard('H', 1),
                    new PlayingCard('S', 2),
                    new PlayingCard('H', 3)
            ));
            // Act
            List<PlayingCard> hearts = hand.getHearts();
            // Assert
            assertEquals(2, hearts.size());
            assertTrue(hearts.stream().allMatch(c -> c.getSuit() == 'H'));
        }

        @Test
        @DisplayName("Should return empty list when no hearts")
        void returnsEmptyListWhenNoHearts() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of(
                    new PlayingCard('S', 1),
                    new PlayingCard('D', 2)
            ));
            // Act & Assert
            assertTrue(hand.getHearts().isEmpty());
        }
    }

    @Nested
    @DisplayName("hasQueenOfSpades()")
    class HasQueenOfSpades {

        @Test
        @DisplayName("Should return true when queen of spades is in hand")
        void returnsTrueWhenQueenOfSpadesInHand() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of(
                    new PlayingCard('S', 12),
                    new PlayingCard('H', 1)
            ));
            // Act & Assert
            assertTrue(hand.hasQueenOfSpades());
        }

        @Test
        @DisplayName("Should return false when queen of spades is not in hand")
        void returnsFalseWhenQueenOfSpadesNotInHand() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of(
                    new PlayingCard('S', 1),
                    new PlayingCard('H', 12)
            ));
            // Act & Assert
            assertFalse(hand.hasQueenOfSpades());
        }

        @Test
        @DisplayName("Should return false when only spades but no queen")
        void returnsFalseWhenOnlySpadesNoQueen() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of(
                    new PlayingCard('S', 1),
                    new PlayingCard('S', 13)
            ));
            // Act & Assert
            assertFalse(hand.hasQueenOfSpades());
        }

        @Test
        @DisplayName("Should return false when only queens but no spades")
        void returnsFalseWhenOnlyQueensNoSpades() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of(
                    new PlayingCard('H', 12),
                    new PlayingCard('D', 12)
            ));
            // Act & Assert
            assertFalse(hand.hasQueenOfSpades());
        }
    }

    @Nested
    @DisplayName("hasFlush()")
    class HasFlush {

        @Test
        @DisplayName("Should return true when hand has 5 cards of same suit")
        void returnsTrueWhenFlush() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of(
                    new PlayingCard('H', 1),
                    new PlayingCard('H', 2),
                    new PlayingCard('H', 3),
                    new PlayingCard('H', 4),
                    new PlayingCard('H', 5)
            ));
            // Act & Assert
            assertTrue(hand.hasFlush());
        }

        @Test
        @DisplayName("Should return false when hand has no flush")
        void returnsFalseWhenNoFlush() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of(
                    new PlayingCard('H', 1),
                    new PlayingCard('S', 2),
                    new PlayingCard('D', 3),
                    new PlayingCard('C', 4),
                    new PlayingCard('H', 5)
            ));
            // Act & Assert
            assertFalse(hand.hasFlush());
        }

        @Test
        @DisplayName("Should return true when hand has more than 5 cards of same suit")
        void returnsTrueWhenMoreThan5OfSameSuit() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of(
                    new PlayingCard('H', 1),
                    new PlayingCard('H', 2),
                    new PlayingCard('H', 3),
                    new PlayingCard('H', 4),
                    new PlayingCard('H', 5),
                    new PlayingCard('H', 6)
            ));
            // Act & Assert
            assertTrue(hand.hasFlush());
        }

        @Test
        @DisplayName("Should return false when hand is empty")
        void returnsFalseWhenHandIsEmpty() {
            // Arrange
            HandOfCards hand = new HandOfCards(List.of());
            // Act & Assert
            assertFalse(hand.hasFlush());
        }
    }
}