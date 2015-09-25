package PokerGame;

import java.util.HashSet;
import java.util.Set;

import PokerExceptions.*;

public class PokerGame {
	Hand[] hands;

	public void reset() {
		hands = null;
		Hand.resetCards();
	}

	public void addHand(int playerId, String carda, String cardb, String cardc, String cardd, String carde)
			throws DuplicateCardException {
		hands[playerId - 1] = new Hand(carda, cardb, cardc, cardd, carde);
	}

	public void setNumPlayers(int numPlayers) {
		hands = new Hand[numPlayers];
	}

	public void getResults() {

	}
}
