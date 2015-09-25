package PokerGame;
import PokerExceptions.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Hand {
	private static Set<String> allCards = new HashSet<String>();

	public static void resetCards(){
		allCards = new HashSet<String>();
	}
	
	Set<String> cards;
	public Hand (String carda, String cardb, String cardc, String cardd, String carde) throws DuplicateCardException{
		cards = new HashSet<String>();
		cards.add(carda);
		cards.add(cardb);
		cards.add(cardc);
		cards.add(cardd);
		cards.add(carde);
		if(cards.size()!=5){
			//see if hand has any duplicates
			throw new PokerExceptions.DuplicateCardException();
		}
		if(Collections.disjoint(allCards, cards)){
			allCards.addAll(cards);
		}
		else{
			//there are duplicates among hands
			throw new PokerExceptions.DuplicateCardException();
		}
		
	}
}
