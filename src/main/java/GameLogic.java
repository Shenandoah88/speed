
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * GameLogic contains all the logic used to play the game California Speed
 * @author shenandoahstubbs
 *
 */
public class GameLogic {

    static List<Card> deck = new ArrayList<Card>();
    static List<Card> playDeck = new ArrayList<Card>();
    static List<Card> playerOne = new ArrayList<Card>();
    static List<Card> playerTwo = new ArrayList<Card>();
    static List<Card> discardPlayerOne = new ArrayList<Card>();
    static List<Card> discardPlayerTwo = new ArrayList<Card>();
    static List<Card> table0 = new ArrayList<Card>();
    static List<Card> table1 = new ArrayList<Card>();
    static List<Card> table2 = new ArrayList<Card>();
    static List<Card> table3 = new ArrayList<Card>();
    static List<Card> table4 = new ArrayList<Card>();
    static List<Card> table5 = new ArrayList<Card>();
    static List<Card> table6 = new ArrayList<Card>();
    static List<Card> table7 = new ArrayList<Card>();
    static Card[] deals = new Card[51];


    /*
     * Creates the 52 card deck for playing
     */
    public static List<Card> createDeck() {
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                Card c = new Card(r, s);
                deck.add(c);
            }
        }
        return deck;
    }

    /**
     * dealGame first shuffles the deck of cards Creates lays out the 8 cards on the
     * table for playing Deals 22 cards to each player for playing
     * adds each card from the table array into an array of itself. These will be used
     * for the discarding and to check matching
     *
     */
    public static void dealGame() {
        createDeck();
        Collections.shuffle(deck);

        deck.toArray(deals);

        // creates 8 playing cards
        for (int i = 0; i < 8; i++) {
            playDeck.add(deck.get(i));
        }
        //create playing hand for player 1
        for (int j = 8; j < 30; j++) {
            playerOne.add(deck.get(j));
        }
        //creates playing hand for player 2
        for(int k = 30; k < 52; k ++)
        {
            playerTwo.add(deck.get(k));
        }

        //Adds each card from the table to a single array to be used
        //for discarding
        table0.add(playDeck.get(0));
        table1.add(playDeck.get(1));
        table2.add(playDeck.get(2));
        table3.add(playDeck.get(3));
        table4.add(playDeck.get(4));
        table5.add(playDeck.get(5));
        table6.add(playDeck.get(6));
        table7.add(playDeck.get(7));



    }

    /**
     * checkTable checks to see if there are any matching cards on the table, if
     * there are it returns true and the game can begin. As soon as it is no longer
     * true the game is paused and restartGame is called.
     */

    public static boolean checkTable() {
        boolean isValid = false;

        for (Card match : playDeck) {
            Rank check = match.getR();
            for (Card checkMatch : playDeck) {
                Rank matchCheck = checkMatch.getR();
                if (matchCheck == check) {
                    return true;
                }
            }
        }
        isValid = false;
        restartGame();
        return isValid;

    }

    /**
     * checkMove checks to see if a player move is valid and allowed meaning that
     * the card was played on a card on the table that had another card on the table
     * that matched in rank
     */

    public static boolean checkMove() {
        boolean valid = checkTable();

        if (checkTable() == true)
        {
            valid = true;

        }
        else
        {
            valid = false;
            restartGame();
        }

        return valid;
    }

    /**
     * This method picks up the 4 piles in front of each player
     * and add them to their hand. This allows the game to continue
     * when there are no matching cards on the table
     */
    public static void pickUpDiscarded()
    {
        //pick up for player one
        for(Card zero : table0)
        {
            for(Card one : table1)
            {
                for(Card two : table2)
                {
                    for(Card three: table3)
                    {
                        playerOne.add(zero);
                        playerOne.add(one);
                        playerOne.add(two);
                        playerOne.add(three);
                    }
                }
            }
        }

        //pick up for player 2
        for(Card four : table4)
        {
            for(Card five : table5)
            {
                for(Card six : table6)
                {
                    for(Card seven : table7)
                    {
                        playerTwo.add(four);
                        playerTwo.add(five);
                        playerTwo.add(six);
                        playerTwo.add(seven);
                    }
                }
            }
        }

        table0.removeAll(table0);
        table1.removeAll(table1);
        table2.removeAll(table2);
        table3.removeAll(table3);
        table4.removeAll(table4);
        table5.removeAll(table5);
        table6.removeAll(table6);
        table7.removeAll(table7);
    }

    public static void redealExisting()
    {
        playDeck.add(playerOne.get(0));
        playDeck.add(playerOne.get(1));
        playDeck.add(playerOne.get(2));
        playDeck.add(playerOne.get(3));
        playDeck.add(playerTwo.get(0));
        playDeck.add(playerTwo.get(1));
        playDeck.add(playerTwo.get(2));
        playDeck.add(playerTwo.get(3));

        table0.add(playDeck.get(0));
        table1.add(playDeck.get(1));
        table2.add(playDeck.get(2));
        table3.add(playDeck.get(3));
        table4.add(playDeck.get(4));
        table5.add(playDeck.get(5));
        table6.add(playDeck.get(6));
        table7.add(playDeck.get(7));
    }
    /**
     * restartGame is called when there are no matches available on the table to
     * play each player picks up the 4 stack of cards in front of them, then each
     * player deals a new 4 cards each for the table
     */

    public static void restartGame()
    {
        pickUpDiscarded();
        redealExisting();

    }

}
