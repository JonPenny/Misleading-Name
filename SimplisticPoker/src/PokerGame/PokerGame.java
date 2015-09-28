package PokerGame;

import java.util.Arrays;

import PokerExceptions.DuplicateCardException;
import PokerExceptions.PlayerNumberException;
import PokerExceptions.UnrecognizedCardException;

/**
 * PokerGame This class oversees all of the logic in the poker game It acts as a
 * genric interface for the whole game
 * 
 * @author jon
 *
 */
public class PokerGame {
	Hand[] hands;

	public PokerGame() {
		reset();
	}

	/**
	 * Clears data stored in the static variables
	 */
	private void reset() {
		hands = null;
		Hand.resetCards();
	}

	/**
	 * This function adds a hand to the game for a specific player
	 * 
	 * @param playerId
	 * @param carda
	 * @param cardb
	 * @param cardc
	 * @param cardd
	 * @param carde
	 * @throws DuplicateCardException
	 * @throws UnrecognizedCardException
	 * @throws PlayerNumberException
	 */
	public void addHand(int playerId, String carda, String cardb, String cardc, String cardd, String carde)
			throws DuplicateCardException, UnrecognizedCardException, PlayerNumberException {

		if (playerId < 0 || playerId > hands.length)
			throw new PokerExceptions.PlayerNumberException();
		hands[playerId - 1] = new Hand(playerId, carda, cardb, cardc, cardd, carde);
	}

	/**
	 * This function sets the number of player participating in this instance of
	 * the game
	 * 
	 * @param numPlayers
	 * @throws PlayerNumberException
	 */
	public void setNumPlayers(int numPlayers) throws PlayerNumberException {
		if (numPlayers < 2 || numPlayers > 4) {
			reset();
			throw new PokerExceptions.PlayerNumberException();
		}
		hands = new Hand[numPlayers];
	}

	/**
	 * This returns a string showing the placement of each of the players in the
	 * game
	 * 
	 * @return
	 * @throws PlayerNumberException
	 */
	public String scoreString() throws PlayerNumberException {
		String results = "Results: \n";
		getResults();
		int score = hands[0].getValue();
		int positions = 1;
		results += hands[0].getPlayer() + ": position 1 \n";
		for (int i = 1; i < hands.length; i++) {
			if (hands[i].getValue() != score) {
				positions++;
			}
			results += hands[i].getPlayer() + ": position " + positions + " \n";

		}
		return results;
	}

	/**
	 * This function updates the scores for all of the hands and returns them as
	 * a sorted array from best to worst
	 * 
	 * @return
	 * @throws PlayerNumberException
	 */
	public Hand[] getResults() throws PlayerNumberException {
		for (int i = 0; i < hands.length; i++) {
			if (hands[i] == null) {
				throw new PokerExceptions.PlayerNumberException();
			}
		}

		Arrays.sort(hands);
		return hands;
	}
}
