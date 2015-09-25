package Tests;

import org.junit.Test;

import PokerExceptions.NumberCardsInHandException;
import PokerExceptions.PlayerNumberException;
import UserInterfaces.TextInterface;
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
		} catch (Exception e) {

		}
		game.reset();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "ThreeHearts", "FourClubs", "OneClubs", "ThreeClubs");
			game.addHand(2, "AceSpades", "FourHearts", "AceClubs", "FiveDiamonds", "SixSpades");
			Assert.fail();
		} catch (Exception e) {

		}
		game.reset();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "AceHearts", "AceClubs", "AceDiamonds", "TwoSpades");
			game.addHand(2, "ThreeSpades", "FourHearts", "NineClubs", "FiveDiamonds", "SixSpades");
		} catch (Exception e) {
			Assert.fail();
		}

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testNumberPlayers() { // Test that only 2,3,4 player can play
		game = new PokerGame();

		// ensure that only the correct number players can join the game
		game.reset();
		try {
			game.setNumPlayers(1);
			Assert.fail();
		} catch (Exception e) {

		}
		try {
			game.setNumPlayers(5);
			Assert.fail();
		} catch (Exception e) {

		}
		try {
			game.setNumPlayers(3);
		} catch (Exception e) {
			Assert.fail();
		}

		// ensure that only the correct number players can be dealed in
		game.reset();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "AceHearts", "AceClubs", "AceDiamonds", "TwoSpades");
			game.addHand(2, "ThreeSpades", "FourHearts", "NineClubs", "FiveDiamonds", "SixSpades");
			game.addHand(3, "FiveSpades", "SixHearts", "NineClubs", "EightDiamonds", "JackSpades");
			game.getResults();
			Assert.fail();
		} catch (Exception e) {

		}

		// ensure that only the correct number players are dealed in
		game.reset();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "ThreeSpades", "FourHearts", "NineClubs", "FiveDiamonds", "SixSpades");
			game.getResults();
			Assert.fail();
		} catch (Exception e) {

		}

		game.reset();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "AceHearts", "AceClubs", "AceDiamonds", "TwoSpades");
			game.addHand(2, "ThreeSpades", "FourHearts", "NineClubs", "FiveDiamonds", "SixSpades");
			game.getResults();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testCardsPerPlayer() { // Make sure that each player submits 5
										// cards
		TextInterface textUi = new TextInterface();
		try {
			textUi.parseNumPlayers("2");
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			textUi.parseHand("ThreeSpades FourHearts NineClubs FiveDiamonds SixSpades");
		} catch (NumberCardsInHandException e) {
			Assert.fail();
		}
		
		try {
			textUi.parseHand("FourHearts NineClubs FiveDiamonds SixSpades");
			Assert.fail();
		} catch (NumberCardsInHandException e) {
			
		}		
	}

	/**
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
