package edu.ntnu.idatt2003.cardgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    private DeckOfCards deck = new DeckOfCards();
    private HandOfCards hand;

    private final TextField handField = new TextField();
    private final TextField sumField = new TextField();
    private final TextField heartsField = new TextField();
    private final TextField queenField = new TextField();
    private final TextField flushField = new TextField();
    private final Label errorLabel = new Label();

    @Override
    public void start(Stage stage) {
        handField.setEditable(false);
        sumField.setEditable(false);
        heartsField.setEditable(false);
        queenField.setEditable(false);
        flushField.setEditable(false);
        errorLabel.setStyle("-fx-text-fill: red;");

        Button dealButton = new Button("Deal hand");
        Button checkButton = new Button("Check hand");
        Button newDeckButton = new Button("New deck");

        dealButton.setOnAction(e -> dealHand());
        checkButton.setOnAction(e -> checkHand());
        newDeckButton.setOnAction(e -> newDeck());

        VBox layout = new VBox(10,
                dealButton, new Label("Hand:"), handField,
                checkButton,
                new Label("Sum of faces:"), sumField,
                new Label("Hearts:"), heartsField,
                new Label("Queen of spades:"), queenField,
                new Label("Flush:"), flushField,
                newDeckButton,
                errorLabel
        );

        layout.setPadding(new Insets(20));

        stage.setScene(new Scene(layout, 400, 500));
        stage.setTitle("Card Game");
        stage.show();
    }

    private void dealHand() {
        if (deck.getDeck().size() < 5) {
            clearResults();
            handField.clear();
            hand = null;
            errorLabel.setText("Not enough cards in deck");
            return;
        }
        errorLabel.setText("");

        hand = deck.dealHand(5);
        String handString = hand.getCards().stream()
                .map(PlayingCard::getAsString)
                .reduce("", (a, b) -> a.isEmpty() ? b : a + " " + b);
        handField.setText(handString);
        clearResults();
    }

    private void checkHand() {
        if (hand == null) {
            errorLabel.setText("No hand dealt yet");
            return;
        }
        errorLabel.setText("");

        sumField.setText(String.valueOf(hand.getSumOfFaces()));

        String hearts = hand.getHearts().stream()
                .map(PlayingCard::getAsString)
                .reduce("", (a, b) -> a.isEmpty() ? b : a + " " + b);
        heartsField.setText(hearts.isEmpty() ? "No Hearts" : hearts);

        queenField.setText(hand.hasQueenOfSpades() ? "Yes" : "No");
        flushField.setText(hand.hasFlush() ? "Flush!" : "No flush");
    }

    private void newDeck() {
        deck = new DeckOfCards();
        hand = null;
        handField.clear();
        clearResults();
        errorLabel.setText("");
    }

    private void clearResults() {
        sumField.clear();
        heartsField.clear();
        queenField.clear();
        flushField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
