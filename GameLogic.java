package speed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * GameLogic contains all the logic used to play the game California Speed
 * @author shenandoahstubbs
 *
 */
public class GameLogic {

	List<Card> deck = new ArrayList<Card>();
	List<Card> playDeck = new ArrayList<Card>();
	List<Card> playerOne = new ArrayList<Card>();
	List<Card> playerTwo = new ArrayList<Card>();
	List<Card> discardPlayerOne = new ArrayList<Card>();
	List<Card> discardPlayerTwo = new ArrayList<Card>();

	/*
	 * Creates the 52 card deck for playing
	 */
	public List<Card> createDeck() {
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
	 */
	public void dealGame() {
		Collections.shuffle(createDeck());

		// creates 8 playing cards
		for (int i = 0; i < 8; i++) {
			Card tableDeal = deck.remove(i);
			playDeck.add(tableDeal);
			// TODO how to create an array from each one of the cards in this ArrayList
		}
		// creates playing hand for each player
		for (int j = 0; j < 22; j++) {
			for (int k = 1; k < 23; k++) {
				Card player1Deal = deck.remove(j);
				Card player2Deal = deck.remove(k);
				playerOne.add(player1Deal);
				playerTwo.add(player2Deal);
			}
		}
	}

	/**
	 * checkTable checks to see if there are any matching cards on the table, if
	 * there are it returns true and the game can begin. As soon as it is no longer
	 * true the game is paused and restartGame is called.
	 */

	public boolean checkTable() {
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

	public boolean checkMove() {
		boolean valid = checkTable();

		if (checkTable() == true)
		{
			valid = true;
			
		} else {
			valid = false;
		}

		return valid;
	}

	/**
	 * restartGame is called when there are no matches available on the table to
	 * play each player picks up the 4 stack of cards in front of them, then each
	 * player deals a new 4 cards each for the table
	 */

	public void restartGame() 
	{
		
	}

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}

	public List<Card> getPlayDeck() {
		return playDeck;
	}

	public void setPlayDeck(List<Card> playDeck) {
		this.playDeck = playDeck;
	}

	public List<Card> getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(List<Card> playerOne) {
		this.playerOne = playerOne;
	}

	public List<Card> getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(List<Card> playerTwo) {
		this.playerTwo = playerTwo;
	}

	public List<Card> getDiscardPlayerOne() {
		return discardPlayerOne;
	}

	public void setDiscardPlayerOne(List<Card> discardPlayerOne) {
		this.discardPlayerOne = discardPlayerOne;
	}

	public List<Card> getDiscardPlayerTwo() {
		return discardPlayerTwo;
	}

	public void setDiscardPlayerTwo(List<Card> discardPlayerTwo) {
		this.discardPlayerTwo = discardPlayerTwo;
	}

}
