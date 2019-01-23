/* Name: Romina Parimi
 * Section Leader: Belce Dogru
 * Purpose: Karel's objective is to place stones(represented as beepers) in the columns
 * that are incomplete, and this construction should work in any size world with
 * columns of any height that are 4 units apart.
 * Citations: Karel Coursereader
 */
import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	
	public void run() {
		while(frontIsClear()) {
			finishOneColumn();
			//move 4 spaces to the next column
			move();
			move();
			move();
			move();
		}
		finishOneColumn();
	}
	/*
	 * Karel will finish filling one column with stones.
	 * Pre:  Karel is at (1, 1) and facing east with unlimited beepers in its bag.
	 * Post: Karel will be facing east and return to the starting position of
	 * when it began constructing the column.
	 */
	private void finishOneColumn() {
		//go there, placing a beeper where necessary
		turnLeft();
		while(frontIsClear()) {
			checkForBeeper();
			move();
		}
		checkForBeeper();
		//come back
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
	}
	/*
	 * Karel will make sure that there is no beeper on a designated space in a column
	 * to avoid placing another beeper where a beeper is already present.
	 * Pre:  Karel's front is clear and is facing north.
	 * Post: Karel will be facing north and put a beeper if and only if there was no
	 * beeper on the current space.
	 */
	private void checkForBeeper() {
		if(noBeepersPresent()) {
			putBeeper();
		}
	}

}