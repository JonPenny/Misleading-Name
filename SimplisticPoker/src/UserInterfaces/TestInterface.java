package UserInterfaces;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import PokerExceptions.DuplicateCardException;
import PokerExceptions.NumberCardsInHandException;
import PokerExceptions.PlayerNumberException;
import PokerExceptions.UnrecognizedCardException;

/**
 * Example of a simple UserInterface
 * 
 * @author jon
 *
 */
public class TestInterface {
	static Scanner scanner;

	public static void main(String args[]) {
		scanner = new Scanner(System.in);
		run();
	}
	public void setScannerInput(File e){
		try {
			scanner = new Scanner(e);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	public static void run(){
		TextInterface game = new TextInterface();
		System.out.println("How many player? 2-4: ");
		try {
			game.parseNumPlayers(scanner.nextLine());
		} catch (NumberFormatException | PlayerNumberException e) {
			System.out.println(e.toString());
			System.out.println("exiting");
		}
		while (true) {
			System.out.println("Input Hands (id card1-5 seperated by spaces) leave blank to view results: ");
			String text = scanner.nextLine();
			if (text.equals("")||text.equals("\n")) {
				try {
					System.out.println(game.getResult());
				} catch (PlayerNumberException e) {
					System.out.println(e.toString());
					System.out.println("exiting");
					}
				break;
			}
			try {
				game.parseHand(text);
			} catch (NumberFormatException | NumberCardsInHandException | DuplicateCardException
					| UnrecognizedCardException | PlayerNumberException e) {
				System.out.println(e.toString());
				System.out.println("exiting");	
			}

		}
	}
	

}
