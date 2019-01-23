/* Name: Romina Parimi
 * Section Leader: Belce Dogru
 * Purpose: This program will display, using nested for loops, a rocket of any size, 
 * denoted by a constant.
 * Citations: The Art and Science of Java
 */
import acm.program.*;

public class Rocket extends ConsoleProgram {
	public static final int SIZE = 3;
	public void run() {
		intro();
		headAndTail();	//head
		bodyTopBottom();
		body();
		bodyTopBottom();
		headAndTail();	//tail
	}
	/*
	 * This method is simply present to display the introductory lines of the program
	 * that include the CS 106A Rocket and the size of the rocket(determined by the value
	 * of the constant).
	 * Pre: Program is running. Nothing is printed to the applet yet.
	 * Post: The program has printed the two lines of output(rocket and rocket size) to 
	 * the applet.
	 */
	private void intro() {
		println("CS 106A Rocket");
		println("(size " + SIZE + ")");
	}
	/*
	 * This method makes the head and tail of the rocket using nested for loops that
	 * are dependent on the size of the rocket.
	 * Pre: The introductory lines have been printed.
	 * Post: The program has made the head or tail of the rocket in a particular size.
	 */
	private void headAndTail() {
		for(int i = 1; i <= SIZE; i++) {
			print(" ");
			for(int j = 1; j <= SIZE-i; j++) {
				print(" ");
			}
			for(int j = 1; j <= i; j++) {
				print("/");
			}
			for(int j = 1; j <= i; j++) {
				print("\\");
			}
			println();
		}
	}
	/*
	 * This method has made the lines for the top and bottom of the body of the rocket.
	 * Pre: The head and/or the body of the rocket as well as the introductory lines 
	 * have been printed.
	 * Post: The program has made the top or bottom of the body of the rocket.
	 */
	private void bodyTopBottom() {
		print("+");
		for(int i = 0; i < SIZE*2; i++) {
			print("=");
		}
		print("+");
		println();
	}
	/*
	 * This method constructs the body of the rocket using nested for loops dependent
	 * on the size of the rocket.
	 * Pre: The introductory lines, the head, and the top line of the body have been
	 * printed to the applet.
	 * Post: The program has completed the body of the rocket.
	 */
	private void body() {
		//start of first half
		for(int i = 1; i <= SIZE; i++) {
			print("|");
			for(int j = 1; j <= SIZE-i; j++) {
				print(".");
			}
			for(int j = 1; j <= i; j++) {
				print("/\\");
			}
			for(int j = 1; j <= SIZE-i; j++) {
				print(".");
			}
			print("|");
			println();
		}
		//start of second half
		for(int i = SIZE; i >= 1; i--) {
			print("|");
			for(int j = 1; j <= SIZE-i; j++) {
				print(".");
			}
			for(int j = 1; j <= i; j++) {
				print("\\/");
			}
			for(int j = 1; j <= SIZE-i; j++) {
				print(".");
			}
			print("|");
			println();
		}
	}	
}