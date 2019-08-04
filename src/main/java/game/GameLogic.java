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
    static  List<Rank> checkArray = new ArrayList<Rank>();
    static int isActiveMatch1;



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
        checkArray.removeAll(checkArray);
        Rank t0 = table0.get(0).getRank();
        Rank t1 = table1.get(0).getRank();
        Rank t2 = table2.get(0).getRank();
        Rank t3 = table3.get(0).getRank();
        Rank t4 = table4.get(0).getRank();
        Rank t5 = table5.get(0).getRank();
        Rank t6 = table6.get(0).getRank();
        Rank t7 = table7.get(0).getRank();

        checkArray.add(t0);
        checkArray.add(t1);
        checkArray.add(t2);
        checkArray.add(t3);
        checkArray.add(t4);
        checkArray.add(t5);
        checkArray.add(t6);
        checkArray.add(t7);

        if(checkArray.contains(t0))
        {
            //find a way to designate this as a matching card to be able to play on
            return true;
        }
        else if(checkArray.contains(t1))
        {
            //find a way to designate this as a matching card to be able to play on
            return true;
        }
       else  if(checkArray.contains(t2))
        {
            //find a way to designate this as a matching card to be able to play on
            return true;
        }
        else if(checkArray.contains(t3))
        {
            //find a way to designate this as a matching card to be able to play on
            return true;
        }
        else if(checkArray.contains(t4))
        {
            //find a way to designate this as a matching card to be able to play on
            return true;
        }
        else if(checkArray.contains(t5))
        {
            //find a way to designate this as a matching card to be able to play on
            return true;
        }
        else if(checkArray.contains(t6))
        {
            //find a way to designate this as a matching card to be able to play on
            return true;
        }
        else if(checkArray.contains(t7))
        {
            //find a way to designate this as a matching card to be able to play on
            return true;
        }
        else
        {
            restartGame();
            return false;
        }

    }

    /**
     * checkMove checks to see if a player move is valid and allowed meaning that
     * the card was played on a card on the table that had another card on the table
     * that matched in rank
     */

    public static boolean checkMove() {
        boolean valid;

        if (checkTable() == true) {
            valid = true;
        } else {
            restartGame();
            valid = false;
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

            String end = "";
            int playStack = playerAction.getStack();

            if(checkMove() != true)
            {
                restartGame();
            }
            else {

                if (playerAction.getPlayer().equals("player1")) {
                    Card PlayCard1 = playerOne.remove(0);
                    if (playStack == 0 && checkMove() == true) {
                        table0.add(0, PlayCard1);
                        ///I believe we need to add to the top, or to the top of
                        //playDeck here so that it is recognized

                    } else if (playStack == 1 && checkMove() == true) {
                        table1.add(0, PlayCard1);
                    } else if (playStack == 2 && checkMove() == true) {
                        table2.add(0, PlayCard1);
                    } else if (playStack == 3 && checkMove() == true) {
                        table3.add(0, PlayCard1);
                    } else if (playStack == 4 && checkMove() == true) {
                        table4.add(0,PlayCard1);
                    } else if (playStack == 5 && checkMove() == true) {
                        table5.add(0,PlayCard1);
                    } else if (playStack == 6 && checkMove() == true) {
                        table6.add(0,PlayCard1);
                    } else if (playStack == 7 && checkMove() == true) {
                        table7.add(0,PlayCard1);
                    }
                    if (playerOne.size() < 1) {
                        end = "END1";
                    }

                }
                if (playerAction.getPlayer().equals("player2")) {
                    Card playCard2 = playerTwo.remove(0);
                    if (playStack == 0 && checkMove() == true) {
                        table0.add(0,playCard2);
                    } else if (playStack == 1 && checkMove() == true) {
                        table1.add(0,playCard2);
                    } else if (playStack == 2 && checkMove() == true) {
                        table2.add(0,playCard2);
                    } else if (playStack == 3 && checkMove() == true) {
                        table3.add(0,playCard2);
                    } else if (playStack == 4 && checkMove() == true) {
                        table4.add(0,playCard2);
                    } else if (playStack == 5 && checkMove() == true) {
                        table5.add(0,playCard2);
                    } else if (playStack == 6 && checkMove() == true) {
                        table6.add(0,playCard2);
                    } else if (playStack == 7 && checkMove() == true) {
                        table7.add(0,playCard2);
                    }
                    if (playerTwo.size() < 1) {
                        end = "END2";
                    }
                }
            }


        GameServerResponse gameServerResponse = new GameServerResponse();
        List<GameBoardSpace> gameBoardSpaces = new ArrayList<>();

        GameBoardSpace gameBoardSpace0 = new GameBoardSpace();
        gameBoardSpace0.setCard(table0.get(0));
        gameBoardSpaces.add(gameBoardSpace0);

        GameBoardSpace gameBoardSpace1 = new GameBoardSpace();
        gameBoardSpace1.setCard(table1.get(0));
        gameBoardSpaces.add(gameBoardSpace1);

        GameBoardSpace gameBoardSpace2 = new GameBoardSpace();
        gameBoardSpace2.setCard(table2.get(0));
        gameBoardSpaces.add(gameBoardSpace2);

        GameBoardSpace gameBoardSpace3 = new GameBoardSpace();
        gameBoardSpace3.setCard(table3.get(0));
        gameBoardSpaces.add(gameBoardSpace3);

        GameBoardSpace gameBoardSpace4 = new GameBoardSpace();
        gameBoardSpace4.setCard(table4.get(0));
        gameBoardSpaces.add(gameBoardSpace4);

        GameBoardSpace gameBoardSpace5 = new GameBoardSpace();
        gameBoardSpace5.setCard(table5.get(0));
        gameBoardSpaces.add(gameBoardSpace5);

        GameBoardSpace gameBoardSpace6 = new GameBoardSpace();
        gameBoardSpace6.setCard(table6.get(0));
        gameBoardSpaces.add(gameBoardSpace6);

        GameBoardSpace gameBoardSpace7 = new GameBoardSpace();
        gameBoardSpace7.setCard(table7.get(0));
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

    /**
     * redealExisting redeals the game from each player's hand if there
     * are no longer any matching cards on the table for them to play on
     */
    public static void redealExisting()
    {
        playDeck.add(0,playerOne.get(0));
        playDeck.add(1,playerOne.get(1));
        playDeck.add(2,playerOne.get(2));
        playDeck.add(3,playerOne.get(3));
        playDeck.add(4,playerTwo.get(0));
        playDeck.add(5, playerTwo.get(1));
        playDeck.add(6, playerTwo.get(2));
        playDeck.add(7, playerTwo.get(3));

        playerOne.remove(0);
        playerOne.remove(1);
        playerOne.remove(2);
        playerOne.remove(3);
        playerTwo.remove(0);
        playerTwo.remove(1);
        playerTwo.remove(2);
        playerTwo.remove(3);

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
