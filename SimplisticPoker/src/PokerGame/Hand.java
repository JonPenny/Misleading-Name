package PokerGame;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import PokerExceptions.DuplicateCardException;
import PokerExceptions.UnrecognizedCardException;

/**
 * This class stores the value of a player hand in poker Its static classes are
 * used to calculate the scores of a players hand They are comparable based off
 * of the value of the hand
 * 
 * @author jon
 *
 */
public class Hand implements Comparable<Hand> {
	private static String[] numbers = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
			"jack", "queen", "king", "ace" };
	private static String[] suits = { "hearts", "diamonds", "clubs", "spades" };

	private static Set<String> allCards = new HashSet<String>();
	private int value = 0;

	public static void resetCards() {
		allCards = new HashSet<String>();
	}

	private int player;
	Set<String> cards;

	/**
	 * This function is used to ensure that all of the cards given in a hand are
	 * all valid There can be no duplicates or made up cards
	 * 
	 * @param cards
	 * @throws DuplicateCardException
	 * @throws UnrecognizedCardException
	 */
	public static void checkValidity(Set<String> cards) throws DuplicateCardException, UnrecognizedCardException {
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
			for (String suit : suits) {
				for (String num : numbers) {
					if ((num + suit).equals(card.toLowerCase())) {
						exists = true;
						break;
					}
				}
				if (exists) {
					break;
				}
			}
			if (exists == false) {
				throw new PokerExceptions.UnrecognizedCardException();
			}
		}

	}

	/**
	 * Helper class the disects a card string and returns the suit
	 * 
	 * @param card
	 * @return
	 */
	private static String getSuit(String card) {
		for (int i = 0; i < suits.length; i++) {
			if (card.toLowerCase().contains(suits[i])) {
				return suits[i];
			}
		}

		return "";
	}

	/**
	 * Helper class the disects a card string and returns the number
	 * 
	 * @param card
	 * @return
	 */
	private static int getNumber(String card) {
		for (int i = 0; i < numbers.length; i++) {
			if (card.toLowerCase().contains(numbers[i])) {
				return i + 1;
			}
		}

		return 0;
	}

	/**
	 * This function takes the cards in the hand and calculates it's score
	 * 
	 * @return
	 */
	private int calculateValue() {
		String[] hand = cards.toArray(new String[cards.size()]);
		// check for Royal Flush (straight ten to ace, same suit) 23
		// check for straight flush (straight, same suit) 22
		// check for straight 18
		{
			// check for 5 in a row
			Set<Integer> numSet = new HashSet<Integer>();
			for (String cc : hand) {
				numSet.add(getNumber(cc));
			}
			if (numSet.size() == 5) {
				// there are no repeat numbers
				Integer[] nums = numSet.toArray(new Integer[numSet.size()]);
				Arrays.sort(nums);
				boolean straight = true;
				int num = nums[0];
				for (int i = 1; i < nums.length; i++) {
					if (nums[i] == num + 1) {
						num += 1;
					} else {
						straight = false;
						break;
					}
				}
				if (straight) {
					// we have a straight!
					Set<String> suitSet = new HashSet<String>();
					for (String i : hand) {
						suitSet.add(getSuit(i));
					}
					if (suitSet.size() == 1) {
						// they are the same suit!
						if (nums[0] == 10) {
							// its royal flush
							return 23;
						}
						// straight flush
						return 22;
					}
					// straight
					return 18;
				}
			}
		}
		{
			// check for flush 19
			Set<String> suitSet = new HashSet<String>();
			for (String i : hand) {
				suitSet.add(getSuit(i));
			}
			if (suitSet.size() == 1) {
				return 19;
			}
		}

		// check for four of a kind (hiest single card) 21
		// check for full house(3 of a kind + 2 of a kind) 20
		// check for three of a kind 17
		// check for Two pair 16
		// check for one pair 15
		{
			Map<Integer, Integer> cardMap = new HashMap<Integer, Integer>();
			for (String cc : hand) {
				if (cardMap.containsKey(getNumber(cc))) {
					cardMap.put(getNumber(cc), cardMap.get(getNumber(cc)) + 1);
				} else {
					cardMap.put(getNumber(cc), 1);
				}
			}
			Collection<Integer> counts = cardMap.values();
			boolean has3 = false;
			boolean has2 = false;
			for (int i : counts) {
				if (i == 4) {
					return 21;
				}
				if (i == 3) {
					has3 = true;
				}
				if (i == 2) {
					if (has2) {
						return 16;
					}
					has2 = true;
				}
			}
			if (has2 && has3) {
				return 20;
			}
			if (has3) {
				return 17;
			}
			if (has2) {
				return 15;
			}

		}
		// check for high card 0-14 0;
		{
			int high = 0;
			for (int i = 0; i < numbers.length; i++) {
				for (String cc : hand) {
					if (cc.toLowerCase().contains(numbers[numbers.length - i - 1])) {
						if (numbers.length - i > high) {
							high = numbers.length - i;
						}
					}
				}
			}
			return high;

		}
	}

	/**
	 * This function returns the value of the hand It calculates it only if the
	 * value hasnt been found before
	 * 
	 * @return
	 */
	public int getValue() {
		if (value == 0) {
			value = calculateValue();
		}
		return value;
	}

	/**
	 * Constructor for a hand
	 * 
	 * @param player:
	 *            the player's ID
	 * @param carda
	 * @param cardb
	 * @param cardc
	 * @param cardd
	 * @param carde
	 * @throws DuplicateCardException
	 * @throws UnrecognizedCardException
	 */
	public Hand(int player, String carda, String cardb, String cardc, String cardd, String carde)
			throws DuplicateCardException, UnrecognizedCardException {
		this.player = player;
		cards = new HashSet<String>();
		cards.add(carda);
		cards.add(cardb);
		cards.add(cardc);
		cards.add(cardd);
		cards.add(carde);
		checkValidity(cards);

	}

	/**
	 * Returns the id of the player the hand belongs to
	 * 
	 * @return
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * Compares 2 hand objects by their value
	 */
	@Override
	public int compareTo(Hand o) {
		if (getValue() > ((Hand) o).getValue()) {
			return -1;
		}
		if (getValue() == ((Hand) o).getValue()) {
			return 0;
		}
		if (getValue() < ((Hand) o).getValue()) {
			return 1;
		}

		return 0;
	}

	/**
	 * To string override to display the cards and score of a playser hand
	 */
	public String toString() {
		String string = "Player " + player + " Score:" + getValue() + " Hand ";
		for (String i : cards) {
			string += i + " ";
		}
		return string;
	}
}
