/* Name: Romina Parimi
 * Section Leader: Belce Dogru
 * Purpose: Karel's objective is to lay down beepers in a checkerboard(alternating)
 * fashion across a world of any size
 * Citations: Karel Coursereader, LAIR
 */
import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run(){
		putBeeper();
		while(leftIsClear()) {
			putBeepers();
			moveUpRow();
		}
		putBeepers();
		moveUpColumn();
	}
	/*
	 * Karel will lay beepers down in an alternating pattern for one row at a time.
	 * Pre:  Karel is at (1, 1) and facing east with unlimited beepers in its bag. There
	 * is already a beeper at (1, 1).
	 * Post: Karel will be facing east at the top of the next row(if there is a row 
	 * above) and will have finished placing beepers in an alternating fashion across
	 * the first row.
	 */
	private void putBeepers(){
		if(frontIsClear() && beepersPresent()) {
			move();
			move();
		}
		else if(frontIsClear()){
			move();
		}
		while(frontIsClear()){
			putBeeper();
			if(noBeepersPresent()){
				if(frontIsClear()) {
					move();
				}
				move();
			}
			move();
			if(frontIsClear()) {
				move();
			}
		}
		turnAround();
		if(frontIsClear()) {
			move();
		}
		turnAround();
		//checking the previous square to see if there is a beeper on it
		if(noBeepersPresent()){
			if(frontIsClear()) {
				move();
			}
			putBeeper();
			
		}
		else {
			if(frontIsClear()) {
				move();
			}
		}
		moveUpRow();
	}
	/*
	 * Karel will move up to the row above(if applicable) to lay out another
	 * set of beepers in an alternating checkerboard pattern.
	 * Pre:  Karel has reached a wall, is facing east and has finished placing beepers
	 * in the current row.
	 * Post: Karel will be facing east at the row above at the first space, at the 
	 * row above at the second space, or turn right if there is no row above.
	 */
	private void moveUpRow(){
		if(frontIsBlocked() && leftIsClear()) {
			turnAround();
			while(frontIsClear()) {
				move();
			}
			turnRight();
			if(beepersPresent() && frontIsClear()) {
				move();
			}
			else {
				move();
				putBeeper();
			}
			turnRight();
		}
	}
	/*
	 * Karel will go one space down the column from the current space to check if
	 * there is a beeper there in order to decide whether or not to put a beeper
	 * on the current space.
	 * Pre:  Karel is facing east at the penultimate row.
	 * Post: Karel will have either placed or not placed a beeper on the first space
	 * of the last row depending on whether or not there was a beeper on the first
	 * space of the penultimate row. It will be facing east.
	 */
	private void moveUpColumn() {
		turnAround();
		if(frontIsBlocked()) {
			turnAround();
			turnRight();
			if(frontIsClear()) {
				move();
			}
			if(beepersPresent()) {
				turnAround();
				if(frontIsClear()) {
					move();
					pickBeeper();
				}
				else {
					if(noBeepersPresent()) {
						putBeeper();
					}
				}
				turnRight();
			}
			else {
				turnAround();
				if(frontIsClear()) {
					move();
				}
			}
		}
	}
}