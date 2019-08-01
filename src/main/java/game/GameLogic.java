package game;

import model.GameBoardSpace;
import model.GameServerResponse;
import model.PlayerAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * game.GameLogic contains all the logic used to play the game California Speed
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
     */
    public static void dealGame() {
        createDeck();
        Collections.shuffle(deck);

        // creates 8 playing cards
        for (int i = 0; i < 8; i++) {
            playDeck.add(deck.get(i));
        }
        //create playing hand for player 1
        for (int j = 8; j < 30; j++) {
            playerOne.add(deck.get(j));
        }
        //creates playing hand for player 2
        for (int k = 30; k < 52; k++) {
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
            Rank check = match.getRank();
            for (Card checkMatch : playDeck) {
                Rank matchCheck = checkMatch.getRank();
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

        if (checkTable() == true) {
            valid = true;

        } else {
            valid = false;
            restartGame();
        }

        return valid;
    }

    /**
     * playerMove takes the player'suit card from their hand and places
     * it on the appropriate stack. In order to do this playerMove
     * gets the id for the player making the move and the id of the stack attempting
     * to be played on.
     */

    public static GameServerResponse playerMove(PlayerAction playerAction) {

            String end;
            Card playCard;
            int playStack = playerAction.getStack();

            if (playerAction.getPlayer().equals("player1")) {
                playCard = playerOne.remove(0);
                if (playStack == 0) {
                    table0.add(playCard);
                } else if (playStack == 1) {
                    table1.add(playCard);
                } else if (playStack == 2) {
                    table2.add(playCard);
                } else if (playStack == 3) {
                    table3.add(playCard);
                } else if (playStack == 4) {
                    table4.add(playCard);
                } else if (playStack == 5) {
                    table5.add(playCard);
                } else if (playStack == 6) {
                    table6.add(playCard);
                } else if (playStack == 7) {
                    table7.add(playCard);
                }
                if(playerOne.size() < 1)
                {
                    end = "END";
                }

            }
            if (playerAction.getPlayer().equals("player2")) {
                playCard = playerTwo.remove(0);
                if (playStack == 0) {
                    table0.add(playCard);
                } else if (playStack == 1) {
                    table1.add(playCard);
                } else if (playStack == 2) {
                    table2.add(playCard);
                } else if (playStack == 3) {
                    table3.add(playCard);
                } else if (playStack == 4) {
                    table4.add(playCard);
                } else if (playStack == 5) {
                    table5.add(playCard);
                } else if (playStack == 6) {
                    table6.add(playCard);
                } else if (playStack == 7) {
                    table7.add(playCard);
                }
               /if(playerTwo.size() < 1)
                {
                    end = "END";
                }
            }
            if(checkMove() == false)
        {
            restartGame();
        }

        GameServerResponse gameServerResponse = new GameServerResponse();
        List<GameBoardSpace> gameBoardSpaces = new ArrayList<>();

        GameBoardSpace gameBoardSpace0 = new GameBoardSpace();
        gameBoardSpace0.setCard(table0.get(table0.size()-1));
        gameBoardSpaces.add(gameBoardSpace0);

        GameBoardSpace gameBoardSpace1 = new GameBoardSpace();
        gameBoardSpace1.setCard(table1.get(table1.size()-1));
        gameBoardSpaces.add(gameBoardSpace1);

        GameBoardSpace gameBoardSpace2 = new GameBoardSpace();
        gameBoardSpace2.setCard(table2.get(table2.size()-1));
        gameBoardSpaces.add(gameBoardSpace2);

        GameBoardSpace gameBoardSpace3 = new GameBoardSpace();
        gameBoardSpace3.setCard(table3.get(table3.size()-1));
        gameBoardSpaces.add(gameBoardSpace3);

        GameBoardSpace gameBoardSpace4 = new GameBoardSpace();
        gameBoardSpace4.setCard(table4.get(table4.size()-1));
        gameBoardSpaces.add(gameBoardSpace4);

        GameBoardSpace gameBoardSpace5 = new GameBoardSpace();
        gameBoardSpace5.setCard(table5.get(table5.size()-1));
        gameBoardSpaces.add(gameBoardSpace5);

        GameBoardSpace gameBoardSpace6 = new GameBoardSpace();
        gameBoardSpace6.setCard(table6.get(table6.size()-1));
        gameBoardSpaces.add(gameBoardSpace6);

        GameBoardSpace gameBoardSpace7 = new GameBoardSpace();
        gameBoardSpace7.setCard(table7.get(table7.size()-1));
        gameBoardSpaces.add(gameBoardSpace7);

        gameServerResponse.setGameBoard(gameBoardSpaces);
        gameServerResponse.setMessage(end);

        return gameServerResponse;
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
