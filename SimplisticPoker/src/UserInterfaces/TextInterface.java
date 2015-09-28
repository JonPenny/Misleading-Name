package UserInterfaces;

import PokerExceptions.DuplicateCardException;
import PokerExceptions.NumberCardsInHandException;
import PokerExceptions.PlayerNumberException;
import PokerExceptions.UnrecognizedCardException;
import PokerGame.PokerGame;

/**
 * This is a module to be used whe implementing the PokerGame using a text interface
 * @author jon
 *
 */
public class TextInterface {
	PokerGame game = new PokerGame();

	public TextInterface() {

	}

	/**
	 * This functiona take a String and parses it as the number of players participating in the game
	 * @param num String of the number of players
	 * @throws NumberFormatException: Number can't be converted to and int
	 * @throws PlayerNumberException: Number is incompatible with the game rules <0 or >4
	 */
	public void parseNumPlayers(String num) throws NumberFormatException, PlayerNumberException {
		game.setNumPlayers(Integer.parseInt(num));
	}

	public void parseHand(String hand) throws NumberCardsInHandException, NumberFormatException, DuplicateCardException,
			UnrecognizedCardException, PlayerNumberException {
		String items[] = hand.split(" ");
		if (items.length != 6) {
			throw new PokerExceptions.NumberCardsInHandException();
		}

		game.addHand(Integer.parseInt(items[0]), items[1], items[2], items[3], items[4], items[5]);

	}

}
