package game;/*
    Class card sets up the constructor for how a card
    is created. Each card has a rank a suit.
    This class will be used to in conjunction with
    game.Suit and game.Rank to create a deck in the game.GameLogic class
    ShenandoahStubbs
 */

public class Card {

    Suit suit;
    Rank rank;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public Rank getRank()
    {
        return rank;
    }

    private String styleString;
    public String getStyleString() {
        if (styleString == null) {
            StringBuilder styleStringBuilder = new StringBuilder();
            int ordinal = getRank().ordinal() + 1;
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
