package model;

public class Card {

    public enum VALUES {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING};
    public enum SUITS {CLUBS, DIAMONDS, HEARTS, SPADES}

    private VALUES value;
    public VALUES getValue() {
        return value;
    }
    public void setValue(VALUES value) {
        this.value = value;
    }

    private SUITS suit;
    public SUITS getSuit() {
        return suit;
    }
    public void setSuit(SUITS suit) {
        this.suit = suit;
    }
}
