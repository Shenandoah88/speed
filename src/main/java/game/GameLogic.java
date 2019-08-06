package game;

import jettydemo.EventSocket;
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
    static List<Card> playerOne = new ArrayList<Card>();
    static List<Card> playerTwo = new ArrayList<Card>();
    static List<ActiveMatchCards> testDeck = new ArrayList<ActiveMatchCards>();
    static List<ActiveMatchCards> playingTable = new ArrayList<ActiveMatchCards>();
    static List<ActiveMatchCards> table0 = new ArrayList<ActiveMatchCards>();
    static List<ActiveMatchCards> table1 = new ArrayList<ActiveMatchCards>();
    static List<ActiveMatchCards> table2 = new ArrayList<ActiveMatchCards>();
    static List<ActiveMatchCards> table3 = new ArrayList<ActiveMatchCards>();
    static List<ActiveMatchCards> table4 = new ArrayList<ActiveMatchCards>();
    static List<ActiveMatchCards> table5 = new ArrayList<ActiveMatchCards>();
    static List<ActiveMatchCards> table6 = new ArrayList<ActiveMatchCards>();
    static List<ActiveMatchCards> table7 = new ArrayList<ActiveMatchCards>();
    static List<Rank> checkArray = new ArrayList<Rank>();

    static Card card0;
    static Card card1;
    static Card card2;
    static Card card3;
    static Card card4;
    static Card card5;
    static Card card6;
    static Card card7;

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
     * table for playing Deals 22 cards to each player for playing adds each card
     * from the table array into an array of itself. These will be used for the
     * discarding and to check matching
     */
    public static void dealGame() {
        createDeck();
        Collections.shuffle(deck);
        int playable = 5;

        // creates 8 playing cards and adds them to a test array to check for matches
        card0 = deck.get(0);
        card1 = deck.get(1);
        card2 = deck.get(2);
        card3 = deck.get(3);
        card4 = deck.get(4);
        card5 = deck.get(5);
        card6 = deck.get(6);
        card7 = deck.get(7);

        ActiveMatchCards a0 = new ActiveMatchCards(card0, false);
        ActiveMatchCards a1 = new ActiveMatchCards(card1, false);
        ActiveMatchCards a2 = new ActiveMatchCards(card2, false);
        ActiveMatchCards a3 = new ActiveMatchCards(card3, false);
        ActiveMatchCards a4 = new ActiveMatchCards(card4, false);
        ActiveMatchCards a5 = new ActiveMatchCards(card5, false);
        ActiveMatchCards a6 = new ActiveMatchCards(card6, false);
        ActiveMatchCards a7 = new ActiveMatchCards(card7, false);

        table0.add(a0);
        table1.add(a1);
        table2.add(a2);
        table3.add(a3);
        table4.add(a4);
        table5.add(a5);
        table6.add(a6);
        table7.add(a7);

        // create playing hand for player 1
        for (int j = 8; j < 30; j++) {
            playerOne.add(deck.get(j));
        }
        // creates playing hand for player 2
        for (int k = 30; k < 52; k++) {
            playerTwo.add(deck.get(k));
        }

    }

    /**
     * checkTable checks to see if there are any matching cards on the table, if
     * there are it returns true and the game can begin. As soon as it is no longer
     * true the game is paused and restartGame is called.
     */
    public static boolean checkTable() {
        boolean active = false;

        testDeck.add(table0.get(0));
        testDeck.add(table1.get(0));
        testDeck.add(table2.get(0));
        testDeck.add(table3.get(0));
        testDeck.add(table4.get(0));
        testDeck.add(table5.get(0));
        testDeck.add(table6.get(0));
        testDeck.add(table7.get(0));

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(i != j)
                {
                    if(testDeck.get(i).getCard().getRank() == testDeck.get(j).getCard().getRank())
                    {
                        testDeck.get(i).playable = true;
                        testDeck.get(j).playable = true;
                        active = true;
                    }
                }
            }
        }

        return active;
    }

    /**
     * checkMove checks to see if a player move is valid and allowed meaning that
     * the card was played on a card on the table that had another card on the table
     * that matched in rank
     */

    public static boolean checkMove() {
        boolean valid;

        if (checkTable()) {
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

        if (playerOne.size() < 1) {
            end = "END1";
        }
        if (playerTwo.size() < 1) {
            end = "END2";
        }

            if(!checkMove())
            {
                restartGame();
            }
            else {

                if (playerAction.getPlayer().equals("player1")) {
                    Card p1 = playerOne.get(0);
                    ActiveMatchCards pa1 = new ActiveMatchCards(p1, false);
                    if (playStack == 0 && table0.get(0).playable) {
                        table0.add(0, pa1);
                        playerOne.remove(p1);
                        checkTable();
                    } else if (playStack == 1 && table1.get(0).playable) {
                        table1.add(0, pa1);
                        playerOne.remove(p1);
                        checkTable();
                    } else if (playStack == 2 && table2.get(0).playable) {
                        table2.add(0, pa1);
                        playerOne.remove(p1);
                        checkTable();
                    } else if (playStack == 3 && table3.get(0).playable) {
                        table3.add(0, pa1);
                        playerOne.remove(p1);
                        checkTable();
                    } else if (playStack == 4 && table4.get(0).playable) {
                        table4.add(0, pa1);
                        playerOne.remove(p1);
                        checkTable();
                    } else if (playStack == 5 && table5.get(0).playable) {
                        table5.add(0, pa1);
                        playerOne.remove(p1);
                        checkTable();
                    } else if (playStack == 6 && table6.get(0).playable) {
                        table6.add(0, pa1);
                        playerOne.remove(p1);
                        checkTable();
                    } else if (playStack == 7 && table7.get(0).playable) {
                        table7.add(0, pa1);
                        playerOne.remove(p1);
                        checkTable();
                    }


                }
                if (playerAction.getPlayer().equals("player2")) {
                    Card p2 = playerTwo.get(0);
                    ActiveMatchCards pa2 = new ActiveMatchCards(p2, false);
                    if (playStack == 0 && table0.get(0).playable) {
                        table0.add(0, pa2);
                        playerTwo.remove(p2);
                        checkTable();
                    } else if (playStack == 1 && table1.get(0).playable) {
                        table1.add(0, pa2);
                        playerTwo.remove(p2);
                        checkTable();
                    } else if (playStack == 2 && table2.get(0).playable) {
                        table2.add(0, pa2);
                        playerTwo.remove(p2);
                        checkTable();
                    } else if (playStack == 3 && table3.get(0).playable) {
                        table3.add(0, pa2);
                        playerTwo.remove(p2);
                        checkTable();
                    } else if (playStack == 4 && table4.get(0).playable) {
                        table4.add(0, pa2);
                        playerTwo.remove(p2);
                        checkTable();
                    } else if (playStack == 5 && table5.get(0).playable) {
                        table5.add(0, pa2);
                        playerTwo.remove(p2);
                        checkTable();
                    } else if (playStack == 6 && table6.get(0).playable) {
                        table6.add(0, pa2);
                        playerTwo.remove(p2);
                        checkTable();
                    } else if (playStack == 7 && table7.get(0).playable) {
                        table7.add(0, pa2);
                        playerTwo.remove(p2);
                        checkTable();
                    }

                }
            }

        GameServerResponse gameServerResponse = new GameServerResponse();
        List<GameBoardSpace> gameBoardSpaces = new ArrayList<>();

        buildGameBoardSpace(gameBoardSpaces, table0, table1, table2, table3, table4, table5, table6, table7);


        gameServerResponse.setGameBoard(gameBoardSpaces);
        gameServerResponse.setMessage(end);

        return gameServerResponse;
    }

    private static void buildGameBoardSpace(List<GameBoardSpace> gameBoardSpaces, List<ActiveMatchCards> table0, List<ActiveMatchCards> table1,
                                            List<ActiveMatchCards> table2, List<ActiveMatchCards> table3, List<ActiveMatchCards> table4,
                                            List<ActiveMatchCards> table5, List<ActiveMatchCards> table6, List<ActiveMatchCards> table7) {
        GameBoardSpace gameBoardSpace0 = new GameBoardSpace();
        gameBoardSpace0.setCard(table0.get(0).getCard());
        gameBoardSpaces.add(gameBoardSpace0);

        GameBoardSpace gameBoardSpace1 = new GameBoardSpace();
        gameBoardSpace1.setCard(table1.get(0).getCard());
        gameBoardSpaces.add(gameBoardSpace1);

        GameBoardSpace gameBoardSpace2 = new GameBoardSpace();
        gameBoardSpace2.setCard(table2.get(0).getCard());
        gameBoardSpaces.add(gameBoardSpace2);

        GameBoardSpace gameBoardSpace3 = new GameBoardSpace();
        gameBoardSpace3.setCard(table3.get(0).getCard());
        gameBoardSpaces.add(gameBoardSpace3);

        GameBoardSpace gameBoardSpace4 = new GameBoardSpace();
        gameBoardSpace4.setCard(table4.get(0).getCard());
        gameBoardSpaces.add(gameBoardSpace4);

        GameBoardSpace gameBoardSpace5 = new GameBoardSpace();
        gameBoardSpace5.setCard(table5.get(0).getCard());
        gameBoardSpaces.add(gameBoardSpace5);

        GameBoardSpace gameBoardSpace6 = new GameBoardSpace();
        gameBoardSpace6.setCard(table6.get(0).getCard());
        gameBoardSpaces.add(gameBoardSpace6);

        GameBoardSpace gameBoardSpace7 = new GameBoardSpace();
        gameBoardSpace7.setCard(table7.get(0).getCard());
        gameBoardSpaces.add(gameBoardSpace7);
    }

    /**
     * This method picks up the 4 piles in front of each player
     * and add them to their hand. This allows the game to continue
     * when there are no matching cards on the table
     */
    public static void pickUpDiscarded() {
        // pick up for player one
        for (ActiveMatchCards zero : table0) {
            Card c0 = zero.getCard();
            for (ActiveMatchCards one : table1) {
                Card c1 = one.getCard();
                for (ActiveMatchCards two : table2) {
                    Card c2 = two.getCard();
                    for (ActiveMatchCards three : table3) {
                        Card c3 = three.getCard();
                        playerOne.add(c0);
                        playerOne.add(c1);
                        playerOne.add(c2);
                        playerOne.add(c3);
                    }
                }
            }
        }

        // pick up for player 2
        for (ActiveMatchCards four : table4) {
            Card c4 = four.getCard();
            for (ActiveMatchCards five : table5) {
                Card c5 = five.getCard();
                for (ActiveMatchCards six : table6) {
                    Card c6 = six.getCard();
                    for (ActiveMatchCards seven : table7) {
                        Card c7 = seven.getCard();
                        playerTwo.add(c4);
                        playerTwo.add(c5);
                        playerTwo.add(c6);
                        playerTwo.add(c7);
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
        ActiveMatchCards c0 = new ActiveMatchCards(playerOne.get(0), false);
        ActiveMatchCards c1 = new ActiveMatchCards(playerOne.get(1), false);
        ActiveMatchCards c2 = new ActiveMatchCards(playerOne.get(2), false);
        ActiveMatchCards c3 = new ActiveMatchCards(playerOne.get(3), false);
        ActiveMatchCards c4 = new ActiveMatchCards(playerTwo.get(0), false);
        ActiveMatchCards c5 = new ActiveMatchCards(playerTwo.get(1), false);
        ActiveMatchCards c6 = new ActiveMatchCards(playerTwo.get(2),false);
        ActiveMatchCards c7 = new ActiveMatchCards(playerTwo.get(3), false);

        table0.add(0, c0);
        table1.add(0, c1);
        table2.add(0, c2);
        table3.add(0, c3);
        table4.add(0, c4);
        table5.add(0, c5);
        table6.add(0, c6);
        table7.add(0, c7);




        playerOne.remove(0);
        playerOne.remove(1);
        playerOne.remove(2);
        playerOne.remove(3);
        playerTwo.remove(0);
        playerTwo.remove(1);
        playerTwo.remove(2);
        playerTwo.remove(3);
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

    public static GameServerResponse getCurrentState() {
        GameServerResponse gameServerResponse = new GameServerResponse();
        List<GameBoardSpace> gameBoardSpaces = new ArrayList<>();
        buildGameBoardSpace(gameBoardSpaces, table0, table1, table2, table3, table4, table5, table6, table7);
        gameServerResponse.setGameBoard(gameBoardSpaces);
        return gameServerResponse;
    }
}
