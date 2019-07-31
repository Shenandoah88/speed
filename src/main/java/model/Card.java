package model;

public class Card {

    public enum VALUES {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING};
    public enum SUITS {CLUBS, DIAMONDS, HEARTS, SPADES};

    private int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    private int suit;
    public int getSuit() {
        return suit;
    }
    public void setSuit(int suit) {
        this.suit = suit;
    }
}
