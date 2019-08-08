package game;

public class ActiveMatchCards {

    boolean playable;
    Card card;

    public ActiveMatchCards(Card card, boolean playable)
    {
        this.playable = playable;
        this.card = card;
    }

    public ActiveMatchCards(Card card)
    {
        this.card = card;
    }


    public Card getCard()
    {
        return card;
    }

    public boolean getPlayable()
    {
        return playable;
    }
}
