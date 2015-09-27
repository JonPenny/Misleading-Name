package PokerGame;

import PokerExceptions.DuplicateCardException;
import PokerExceptions.PlayerNumberException;
import PokerExceptions.UnrecognizedCardException;

public class PokerGame {
	Hand[] hands;

	public void reset() {
		hands = null;
		Hand.resetCards();
	}

	public void addHand(int playerId, String carda, String cardb, String cardc, String cardd, String carde)
			throws DuplicateCardException, UnrecognizedCardException {
		hands[playerId - 1] = new Hand(carda, cardb, cardc, cardd, carde);
	}

	public void setNumPlayers(int numPlayers) throws PlayerNumberException {
		if(numPlayers<2||numPlayers>4){
			reset();
			throw new PokerExceptions.PlayerNumberException();
		}
		hands = new Hand[numPlayers];
	}

	public void getResults() throws PlayerNumberException {
		for(int i=0; i<hands.length; i++){
			if(hands[i]==null){
				reset();
				throw new PokerExceptions.PlayerNumberException();
			}
		}
	}
}
