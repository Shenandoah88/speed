import java.util.Random;
/*
    GameLogic is the class used to generate all the logic and game methods
    that create the rules for playing California Speed
    ShenandoahStubbs
 */
public class GameLogic {
    Card[] deck = new Card[52];
    //creteDeck creates the deck of 52 playing cards used for the game
    public void createDeck (Card[] deck)
    {
        int i = 0;
        for(Suit s: Suit.values())
        {
            for(Rank r: Rank.values())
            {
                deck[i] = new Card(r, s);
                i++;
            }
        }
    }

    /*
         Sedgewick's exchange method used with the shuffle
     */
    public static void exch(Card[] a, int i, int j)
    {
        Card swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    /*
        Sedgewick shuffle code for an array. This will shuffle the deck so that the order
        is always different
     */
    public static void shuffle(Card[] a)
    {
      int n = a.length;
      for(int i = 0; i< n; i++)
      {
          int r = i + (int)(Math.random() * (n-i));
          exch(a, i, r);
      }
    }
    /*
        dealGame will deal the 8 cards on the table and each player 22 cards
     */

    public void dealGame()
    {
        createDeck(deck);
        shuffle(deck);
    //TODO need to deal 8 cards to the table and 22 cards to each player. Create an array for each. 

    }
    /*
        checkMove checks to see if the players move is valid
        The players move is valid if the card they played upon has a matching
        Rank on the board at the time of the play
     */
    public boolean checkMove()
    {
        //TODO need to check to see which cards are active on the table and if the move is allowed
        return true;
    }


}

