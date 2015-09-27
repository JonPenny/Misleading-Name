package PokerGame;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import PokerExceptions.DuplicateCardException;
import PokerExceptions.UnrecognizedCardException;

public class Hand implements Comparable {
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

	private int calculateValue() {
		String[] hand = cards.toArray(new String[cards.size()]);
		// check for Royal Flush (straight ten to ace, same suit) 23

		// check for straight flush (straight, same suit) 22

		// check for flush 19

		// check for straight 18

		// check for four of a kind (hiest single card) 21
		// check for full house(3 of a kind + 2 of a kind) 20
		// check for three of a kind 17
		// check for Two pair 16
		// check for one pair 15

		// check for high card 0-14 0;
		{
			int high = 0;
			for (int i = 0; i < numbers.length; i++) {
				for (String cc : hand) {

					if (cc.toLowerCase().contains(numbers[numbers.length - i - 1])) {
						if (numbers.length - i > high) {
							System.out.println(cc + (numbers[numbers.length - i - 1]));
							high = numbers.length - i;
						}
					}
				}
			}
			return high;

		}
	}

	public int getValue() {
		if (value == 0) {
			value = calculateValue();
		}
		return value;
	}

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

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Hand) {
			if (getValue() > ((Hand) o).getValue()) {
				return -1;
			}
			if (getValue() == ((Hand) o).getValue()) {
				return 0;
			}
			if (getValue() < ((Hand) o).getValue()) {
				return 1;
			}
		}
		return 0;
	}

	public String toString() {
		String string = "Player " + player + " Score:" + getValue() + " Hand ";
		for (String i : cards) {
			string += i + " ";
		}
		return string;
	}
}
