/* Name: Romina Parimi
 * Section Leader: Belce Dogru
 * Purpose: This program will compute the Hailstone sequence for a particular integer
 * (based on user input), and it will run as many times as the user wants.
 * Citations: The Art and Science of Java
 */
import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		println("This program computes Hailstone sequences.");
		int x = readInt("Enter a number: ");
		doHailstone(x);
		//ask to play again
		while (readBoolean("Run again? ", "y", "n")) {
			if(true) {
				x = readInt("Enter a number: ");
				doHailstone(x);
			}
		}
		println("Thanks for using Hailstone.");
	}
	/*
	 * The Hailstone sequence is computed by multiplying odd numbers by 3 and adding 1, 
	 * and it halves even numbers. The sequence is complete when the integer becomes 1.
	 * Pre: The user has entered an integer.
	 * Post: The program has computed the Hailstone sequence for the given integer(has
	 * reached 1. All the intermediate steps to get to 1 have been displayed.
	 */
	private void doHailstone(int x) {
		int steps = 0;
		while(x != 1) {
			//if number is even, x/2
			if(x % 2 == 0) {
				print(x + " is even, so I take half: ");
				x /= 2;
				println(x);
				steps++;
			} else {	//if number is odd, 3x+1
				print(x + " is odd, so I make 3n + 1: ");
				x = 3*x + 1;
				println(x);
				steps++;
			}
		}
		//x is now 1
		//print number of steps
		println("It took " + steps + " steps to reach 1.");
	}
	
}