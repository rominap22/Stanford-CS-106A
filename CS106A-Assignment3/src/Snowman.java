/* Names: Romina Parimi, Giovanni Thompson
 * Section Leader: Belce Dogru
 * Purpose: The objective of this program is to 
 * play a game of Snowman for as many times as the user wants, but the user has a 
 * finite number of wrong guesses to guess a word(length of choice). It simultaneously 
 * constructs a snowman for each incorrectly guessed letter. Once the user does not to 
 * play any more games, the program will display the statistics for the played games, 
 * including their games played, games won, and their best game.
 * Citations: The Art and Science of Java
 */
import acm.program.*;

import acm.util.*;
import java.io.*; // for File
import java.util.*; // for Scanner

public class Snowman extends SnowmanProgram {
	
	static final int MAX_LIVES = 8;

	public void run() {
		int gamesCount = 0;
		int gamesWon = 0;
		int best = 0;
		intro();
		String fileName = promptUserForFile("Enter file name: ", "res/");
		boolean playAgain = true;
		while (playAgain) {
			gamesCount++;
			int guessCount = playOneGame(getRandomWord(fileName));
				if(guessCount > 0) {
					gamesWon++;
				}
				if(guessCount > best) {
					best = guessCount;
				}
			playAgain = readBoolean ("Play Again (Y/N)?","Y","N");
			if(playAgain) {
				fileName = promptUserForFile("Enter file name: ", "res/");
			}
		}
		stats(gamesCount, gamesWon, best);
	}

	/*
	 * This method will output the initial message of the program that introduces the 
	 * user to the game of Snowman and the instructions necessary to play. 
	 * Pre: The user has run the program.
	 * Post: The program has displayed the Snowman game directions and the introductory 
	 * message to the user's applet.
	 */	
	private void intro() {
		String introduction = "CS 106A Snowman!\n" + "I will think of a random word.\n"
				+ "You'll try to guess its letters.\n" + "Every time you guess a letter\n"
				+ "that isn't in my word, a new\n" + "piece of the snowman appears.\n" 
				+ "Guess correctly to avoid\n" + "bringing him to life in the sun!\n";

		println(introduction);
		println();
	}
	
	/*
	 * This method will allow the user to play one game of Snowman while keeping track 
	 * of the guessed letters, guesses left, and periodically updating the dashes of 
	 * the secret word until the user guesses the word correctly or has no more guesses 
	 * remaining.
	 * Pre: The instructions have been displayed, and the user has entered the file name
	 * as the source of their secret word. The hint has also been created and displayed.
	 * Post: The program has played a full game iteration, taking into account the 
	 * guessed letters and updating the guess count and hint as needed.
	 */
	private int playOneGame(String secretWord) {
		String guessedLetters = "";
		int guessCount = MAX_LIVES;
		String hint = createHint(secretWord, guessedLetters);
		boolean nextGuess = true;
		displaySnowman(guessCount);

		while (nextGuess) {
			println("Secret Word : " + hint);
			println("Your Guesses: " + guessedLetters);
			println("Guesses left: " + guessCount);
			char formattedGuess = readGuess(guessedLetters);
			if (secretWord.indexOf(formattedGuess) != -1) {
				println("Correct!");
			} else {
				println("Incorrect.");
				guessCount--;
			}
			guessedLetters += formattedGuess;
			hint = createHint(secretWord, guessedLetters);
			nextGuess = !hint.equals(secretWord) && guessCount > 0;
			displaySnowman(guessCount);
		}
		if (guessCount > 0) {
			println("You Win! My word was: " + secretWord);
		} else {
			println("You lose! My word was: " + secretWord);
		}
		return guessCount;
	}

	/*
	 * This method will create the hint for the secret word out of dashes(number of
	 * dashes equals the length of the secret word), and the hint is updated periodically
	 * as the user guesses letters correctly.
	 * Pre: The instructions have been displayed, and the user has entered the file name
	 * as the source of their secret word.
	 * Post: The program has displayed the number of dashes that correspond to each letter
	 * of the secret word.
	 */
	private String createHint(String secretWord, String guessedLetters) {
		String hint = "";
		for (int i = 0; i < secretWord.length(); i++) {
			String currentLetter = secretWord.substring(i, i + 1);
			if (guessedLetters.contains(currentLetter)) {
				hint += currentLetter;
			} else {
				hint += "-";

			}
		}
		return hint;
	}

	/*
	 * This method will ask the user for their guess until they have no guesses 
	 * remaining or guessed the secret word correctly. It also checks that the user entered a
	 * letter from A to Z.
	 * Pre: The instructions have been displayed, the user has entered the file name, and the hint has been displayed.
	 * Post: The program has updated the hint after reading the user's guess entry.
	 */
	private char readGuess(String guessedLetters) {
		String userGuess;
		char letter = ' ';
		boolean askAgain;
		do {
			askAgain = true;
			userGuess = readLine("Your guess? ").toUpperCase();
			if (userGuess.length() != 1) {
				println("Type a single letter from A-Z.");
			} else {
				letter = userGuess.charAt(0);
				if (letter < 'A' || letter > 'Z') {
					println("Type a letter from A-Z.");
				} else if (guessedLetters.contains(userGuess)) {
					println("You already guessed that letter.");
				} else {
					askAgain = false;
				}
			}
		} while (askAgain);
		return letter;
	}

	/*
	 * This method will read the snowman display from a file and construct it while the user guesses incorrectly.
	 * Pre: The instructions have been displayed, the user has entered the file name, and the hint has been displayed.
	 * Post: The program has read the Snowman file to display after the hint and is periodically updated.
	 */
	private void displaySnowman(int guessCount) {
		try {
			File myFile = new File("res/display" + guessCount + ".txt");
			Scanner s = new Scanner(myFile);
			while (s.hasNextLine()) {
				println(s.nextLine());
			}
		} catch (FileNotFoundException ex) {
			println("File not found" + ex);
		}
	}

	/*
	 * This method will display the statistics of the games that the user played so far, including their best game, total games played, 
	 * the win percent, and games won.
	 * Pre: The user has entered "n" or "N" for discontinuing playing any more games.
	 * Post: The program displayed all the statistics to the applet, and the program is complete.
	 */
	private void stats(int gamesCount, int gamesWon, int best) {
		println("Overall statistics:");
		println("Games played: " + gamesCount);
		println("Games won: " + gamesWon);
		println("Win percent: " + (((double)gamesWon/(double)gamesCount)*100) + "%");
		println("Best game: " + best + " guess(es) remaining");
		println("Thanks for playing!");
	}

	/*
	 * This method will pick a random word from the .txt file of the user's choice and throw an exception if the file is not found.
	 * Pre: The instructions have been displayed. The program has prompted the user for a file name to extract the secret word.
	 * Post: The program has generated a random word(in the form of dashes) from the user's file of choice or thrown an exception.
	 */
	private String getRandomWord(String filename) {
		try {
			Scanner s = new Scanner(new File(filename));
			int random = RandomGenerator.getInstance().nextInt(s.nextInt());
			for (int i = random; i > 0; i--) {
				s.next();
			}
			return s.next();
		} catch (FileNotFoundException ex) {
			println("File not found" + ex);
		}
		return "";
	}
}