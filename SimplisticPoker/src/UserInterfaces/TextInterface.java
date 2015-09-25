package UserInterfaces;

import PokerExceptions.NumberCardsInHandException;
import PokerExceptions.PlayerNumberException;
import PokerGame.PokerGame;

public class TextInterface {
	PokerGame game = new PokerGame();

	public TextInterface() {

	}

	public void parseNumPlayers(String num) throws NumberFormatException, PlayerNumberException {
		game.setNumPlayers(Integer.parseInt(num));
	}

	public void parseHand(String hand) throws NumberCardsInHandException {
		String items[] = hand.split(" ");
		if (items.length != 5) {
			throw new PokerExceptions.NumberCardsInHandException();
		}
		// game.addHand(playerId, carda, cardb, cardc, cardd, carde);
	}

}
