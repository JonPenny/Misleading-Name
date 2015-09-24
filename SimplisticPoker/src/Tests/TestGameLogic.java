package Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import PokerExceptions.*;
import PokerGame.PokerGame;
import junit.framework.Assert;

public class TestGameLogic {
	PokerGame game;

	@SuppressWarnings("deprecation")
	@Test
	public void testDuplicates() {
		// Make sure that the same card doesn't appear twice
		
		game = new PokerGame();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "AceSpades", "AceSpades", "AceSpades", "AceSpades");
			game.addHand(2, "ThreeSpades", "FourHearts", "AceClubs", "FiveDiamonds", "SixSpades");
			Assert.fail();
		} catch (DuplicateCardException e) {
			
		}
		game.reset();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "ThreeHearts", "FourClubs", "OneClubs", "ThreeClubs");
			game.addHand(2, "AceSpades", "FourHearts", "AceClubs", "FiveDiamonds", "SixSpades");
			Assert.fail();
		} catch (DuplicateCardException e) {
			
		}
		game.reset();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "AceHearts", "AceClubs", "AceDiamonds", "TwoSpades");
			game.addHand(2, "ThreeSpades", "FourHearts", "NineClubs", "FiveDiamonds", "SixSpades");
		} catch (DuplicateCardException e) {
			Assert.fail();
		}

	}

	/**
	 * @Test public void testNumberPlayers(){ //Test that only 2,3,4 player can
	 *       play
	 * 
	 *       }
	 * 
	 * @Test public void testCardsPerPlayer(){ //Make sure that each player
	 *       submits 5 cards
	 * 
	 *       }
	 * 
	 * @Test public void testHandRanks(){ //Ensure that cards are ranked
	 *       properly
	 * 
	 *       }
	 * 
	 * @Test public void testDuplicateHands(){ //Make sure that hands are ranked
	 *       equal
	 * 
	 *       }
	 * 
	 * @Test public void testPlayerIds(){ //Ensure that that ids given are used
	 * 
	 *       }
	 * 
	 * @Test public void testOrderEntry(){ //make sure that subissions are id
	 *       then cards }
	 **/
}
