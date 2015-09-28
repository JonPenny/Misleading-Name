package PokerGame;

import java.util.Arrays;

import PokerExceptions.DuplicateCardException;
import PokerExceptions.PlayerNumberException;
import PokerExceptions.UnrecognizedCardException;

public class PokerGame {
	Hand[] hands;
	public PokerGame(){
		reset();
	}
	
	private void reset() {
		hands = null;
		Hand.resetCards();
	}

	public void addHand(int playerId, String carda, String cardb, String cardc, String cardd, String carde)
			throws DuplicateCardException, UnrecognizedCardException, PlayerNumberException {
		
		if(playerId<0||playerId>hands.length)
			throw new PokerExceptions.PlayerNumberException();
		hands[playerId - 1] = new Hand(playerId ,carda, cardb, cardc, cardd, carde);
	}

	public void setNumPlayers(int numPlayers) throws PlayerNumberException {
		if(numPlayers<2||numPlayers>4){
			reset();
			throw new PokerExceptions.PlayerNumberException();
		}
		hands = new Hand[numPlayers];
	}

	public String scoreString() throws PlayerNumberException{
		String results = "Results: \n";
		getResults();
		int score = hands[0].getValue();
		int positions =1;
		results += hands[0].getPlayer() + ": position 1 \n";
		for(int i  =1; i< hands.length; i++){
			if(hands[i].getValue()!=score){
				positions++;
			}
			results += hands[i].getPlayer() + ": position "+positions +" \n";

		}
		return results;
	}
	
	public Hand[] getResults() throws PlayerNumberException {
		for(int i=0; i<hands.length; i++){
			if(hands[i]==null){
				reset();
				throw new PokerExceptions.PlayerNumberException();
			}
		}
		Arrays.sort(hands);
		return hands;
	}
}
