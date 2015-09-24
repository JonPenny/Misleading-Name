package Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import PokerExceptions.*;
import PokerGame.PokerGame;
import junit.framework.Assert;

public class TestGameLogic {
	PokerGame game;
	game = new PokerGame();
	
	@Test
	public void testDuplicates(){
		//Make sure that the same card doesn't appear twice
		game.reset();
		game.setPlayers(2);
		game.addHand(1,"AceSpades","AceSpades","AceSpades","AceSpades","AceSpades");
		game.addHand(2,"AceSpades","AceSpades","AceSpades","AceSpades","AceSpades");
		String result = game.getResults();
		
		Assert.assertTrue(result.equals("Invalid Hands - Duplicat Cards"));
		
		game.reset();
		game.setPlayers(2);
		game.addHand(1,"AceSpades","AceSpades","AceSpades","AceSpades","AceSpades");
		game.addHand(2,"AceSpades","AceSpades","AceSpades","AceSpades","AceSpades");
		String result = game.getResults();
		
		Assert.assertTrue(result.equals("Invalid Hands - Duplicat Cards"));
	}
	
	/**
	@Test
	public void testNumberPlayers(){
		//Test that only 2,3,4 player can play
		
	}
	
	@Test
	public void testCardsPerPlayer(){
		//Make sure that each player submits 5 cards
		
	}
	
	@Test
	public void testHandRanks(){
		//Ensure that cards are ranked properly
	
	}
	
	@Test
	public void testDuplicateHands(){
		//Make sure that hands are ranked equal
		
	}

	@Test
	public void testPlayerIds(){
		//Ensure that that ids given are used

	}

	@Test
	public void testOrderEntry(){
		//make sure that subissions are id then cards
	}
	**/
}
