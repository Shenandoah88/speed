package game;/*
    Class card sets up the constructor for how a card
    is created. Each card has a rank a suit.
    This class will be used to in conjunction with
    game.Suit and game.Rank to create a deck in the game.GameLogic class
    ShenandoahStubbs
 */

public class Card {

    Suit s;
    Rank r;

    public Card(Rank r, Suit s) {
        this.r = r;
        this.s = s;
    }

    public Suit getS()
    {
        return s;
    }

    public Rank getR()
    {
        return r;
    }

}
