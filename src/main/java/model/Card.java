package model;

public class Card {

    public enum RANK {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING};
    public enum SUIT {CLUBS, DIAMONDS, HEARTS, SPADES}

    private RANK value;
    public RANK getValue() {
        return value;
    }
    public void setValue(RANK value) {
        this.value = value;
    }

    private SUIT suit;
    public SUIT getSuit() {
        return suit;
    }
    public void setSuit(SUIT suit) {
        this.suit = suit;
    }

    private String styleString;
    public String getStyleString() {
        if (styleString == null) {
            StringBuilder styleStringBuilder = new StringBuilder();
            int ordinal = getValue().ordinal() + 1;
            switch (ordinal) {
                case 1:
                    styleStringBuilder.append("A");
                    break;
                case 11:
                    styleStringBuilder.append("J");
                    break;
                case 12:
                    styleStringBuilder.append("Q");
                    break;
                case 13:
                    styleStringBuilder.append("K");
                    break;
                default:
                    styleStringBuilder.append(ordinal);
            }
            styleStringBuilder.append(getSuit().name().charAt(0));

            styleString = styleStringBuilder.toString();
        }

        return styleString;
    }
    public void setStyleString(String styleString) {
        this.styleString = styleString;
    }
}
