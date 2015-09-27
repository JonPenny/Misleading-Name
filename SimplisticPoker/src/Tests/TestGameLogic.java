package Tests;

import org.junit.Test;

import PokerExceptions.NumberCardsInHandException;
import PokerExceptions.PokerException;
import PokerGame.Hand;
import PokerGame.PokerGame;
import UserInterfaces.TextInterface;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TestGameLogic {
	PokerGame game;

	@Test
	public void testDuplicates() {
		// Make sure that the same card doesn't appear twice

		game = new PokerGame();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "AceSpades", "AceSpades", "AceSpades", "AceSpades");
			game.addHand(2, "ThreeSpades", "FourHearts", "AceClubs", "FiveDiamonds", "SixSpades");
			Assert.fail();
		} catch (PokerException e) {

		}
		game = new PokerGame();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "ThreeHearts", "FourClubs", "OneClubs", "ThreeClubs");
			game.addHand(2, "AceSpades", "FourHearts", "AceClubs", "FiveDiamonds", "SixSpades");
			Assert.fail();
		} catch (PokerException e) {

		}
		game = new PokerGame();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "AceHearts", "AceClubs", "AceDiamonds", "TwoSpades");
			game.addHand(2, "ThreeSpades", "FourHearts", "NineClubs", "FiveDiamonds", "SixSpades");
		} catch (PokerException e) {
			Assert.fail();
		}

	}

	@Test
	public void testNumberPlayers() { // Test that only 2,3,4 player can play
		game = new PokerGame();

		// ensure that only the correct number players can join the game
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
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "AceHearts", "AceClubs", "AceDiamonds", "TwoSpades");
			game.addHand(2, "ThreeSpades", "FourHearts", "NineClubs", "FiveDiamonds", "SixSpades");
			game.addHand(3, "FiveSpades", "SixHearts", "NineClubs", "EightDiamonds", "JackSpades");
			game.getResults();
			Assert.fail();
		} catch (PokerException e) {

		}

		// ensure that only the correct number players are dealed in
		game = new PokerGame();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "ThreeSpades", "FourHearts", "NineClubs", "FiveDiamonds", "SixSpades");
			game.getResults();
			Assert.fail();
		} catch (PokerException e) {

		}

		game = new PokerGame();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "AceHearts", "AceClubs", "AceDiamonds", "TwoSpades");
			game.addHand(2, "ThreeSpades", "FourHearts", "NineClubs", "FiveDiamonds", "SixSpades");
			game.getResults();
		} catch (PokerException e) {
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

	@Test
	public void testCardInput() {
		// ensure the given cards are valid
		game = new PokerGame();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "ThreeSpades", "FourHearts", "NineClubs", "FiveDiamonds", "SixSpades");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

		game = new PokerGame();
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpadees", "JackHurts", "AceClubs", "AceDiamonds", "TwoSpades");
			Assert.fail();
		} catch (Exception e) {

		}

	}

	@Test
	public void testHandRanks() { // Ensure that cards are ranked properly
		game = new PokerGame();
		
		//check for high card
		try {
			game.setNumPlayers(2);
			game.addHand(1, "AceSpades", "AceHearts", "AceClubs", "AceDiamonds", "TwoSpades");
			game.addHand(2, "ThreeSpades", "FourHearts", "NineClubs", "FiveDiamonds", "SixSpades");
			Hand res[] = game.getResults();
			Assert.assertTrue(res[0].getPlayer()==1);
		} catch (PokerException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
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
