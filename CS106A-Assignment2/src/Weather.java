/* Name: Romina Parimi
 * Section Leader: Belce Dogru
 * Purpose: This program will store temperatures(based on user input) until a sentinel
 * value is reached, and then it will display the highest and lowest temperatures, the
 * average of the temperatures, and the number of cold days(temperature is less than
 * or equal to 50).
 * Citations: The Art and Science of Java
 */
import acm.program.*;

public class Weather extends ConsoleProgram {
	public static final int SENTINEL = -1;
	public void run() {
		println("CS 106A \"Weather Master 4000\"!");
		
		//initial comparison value
		int input1 = readInt("Next temperature (or " + SENTINEL + " to quit)? ");
		int min = input1;
		int max = input1;
		int tempsum = input1;
		int numtemps = 1;
		int colddays = 0;
		int input;
		//checks if it is a cold day
		if(input1 <= 50) {
			colddays++;
		}
		//if temp entered the first time is sentinel, say no temperatures were entered
		if(min == SENTINEL) {
			println("No temperatures were entered.");
		} else {	////keeps reading input otherwise
			input = readInt("Next temperature (or " + SENTINEL + " to quit)? ");
			while(input != SENTINEL) {
				//add to sum and number of temps(for calculating avg later)
				tempsum += input;
				numtemps++;
				//updates max temp
				if(input > max) {
					max = input;
				}
				//updates min temp
				if(input < min) {
					min = input;
				}
				//check if it is a cold day
				if(input <= 50) {
					colddays++;
				}
				//read the next value
				input = readInt("Next temperature (or " + SENTINEL + " to quit)? ");
			}
			//user has entered sentinel value
			//display highest temp
			println("Highest temperature = " + max);
			//display lowest temp
			println("Lowest temperature = " + min);
			//calculate average
			double tempsum1 = tempsum;
			double numtemps1 = numtemps;
			println("Average = " + (tempsum1/numtemps1));
			//display number of cold days
			println(colddays + " cold day(s).");
		}
	}
}