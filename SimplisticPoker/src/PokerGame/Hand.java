package PokerGame;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import PokerExceptions.DuplicateCardException;
import PokerExceptions.UnrecognizedCardException;

public class Hand {
	private static String[] numbers = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack",
			"queen", "king", "ace" };
	private static String[] suits = { "hearts", "diamonds", "clubs", "spades" };

	private static Set<String> allCards = new HashSet<String>();

	public static void resetCards() {
		allCards = new HashSet<String>();
	}

	Set<String> cards;

	public static void checkValidity(Set<String> cards) throws DuplicateCardException, UnrecognizedCardException{
		// see if hand has any duplicates
				if (cards.size() != 5) {
					throw new PokerExceptions.DuplicateCardException();
				}
				// check if there are duplicates among hands
				if (Collections.disjoint(allCards, cards)) {
					allCards.addAll(cards);
				} else {

					throw new PokerExceptions.DuplicateCardException();
				}
				// check that he inputed cards are valid
				for (String card : cards) {
					boolean exists = false;
					for(String suit: suits){
						for(String num: numbers){
							if((num+suit).equals(card.toLowerCase())){
								exists=true;
								break;
							}
						}
						if(exists){
							break;
						}
					}
					if(exists==false){
						throw new PokerExceptions.UnrecognizedCardException();
					}
				}

	}
	public Hand(String carda, String cardb, String cardc, String cardd, String carde) throws DuplicateCardException, UnrecognizedCardException {
		cards = new HashSet<String>();
		cards.add(carda);
		cards.add(cardb);
		cards.add(cardc);
		cards.add(cardd);
		cards.add(carde);
		checkValidity(cards);
		
	}
}
