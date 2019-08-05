package game;

public class ActiveMatchCards {

    boolean playable;
    Card card;
    int indexMatch;

    public ActiveMatchCards(Card card, boolean playable, int indexMatch)
    {
        this.playable = playable;
        this.card = card;
        this.indexMatch = indexMatch;
    }
    public ActiveMatchCards(Card card, boolean playable)
    {
        this.playable = playable;
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

    public int getIndexMatch()
    {
        return indexMatch;
    }
}
