/* Name: Romina Parimi
 * Section Leader: Belce Dogru
 * Purpose: Karel's objective is to locate the midpoint of a world of any size, place
 * a beeper on that space, and be on that space as its final position.
 * Citations: Karel Coursereader, LAIR
 */
import stanford.karel.*;

public class MidpointFinderKarel extends SuperKarel {
	public void run() {
		putEndBeepers();
		findMiddle();
		pickBeepers();
		if(frontIsClear()) {
			move();	//skips past the middle space
		}
		pickBeepers();
		turnAround();
	}
	/*
	 * Karel will lay a beeper down at (1, 1) and the last space in the first street.
	 * Pre: Karel is facing east at (1, 1) and has an unlimited number of beepers.
	 * Post: Karel will be facing east at (1, 1) and will have placed a beeper at (1, 1)
	 * and the last space of the first street.
	 */
	private void putEndBeepers() {
		if(frontIsClear()) {
			putBeeper();
		}
		while(frontIsClear()) {
			move();
		}
		putBeeper();
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnAround();
		if(frontIsClear()) {
			move();
		}
	}
	/*
	 * Karel will find the middle of the street by laying beepers down on either end
	 * until it reaches a midpoint.
	 * Pre: Karel has placed beepers on either end of the first street.
	 * Post: Karel will be facing west and be positioned at the midpoint of its world.
	 */
	private void findMiddle() {
		while(noBeepersPresent()) {
			while(noBeepersPresent() && frontIsClear()) {
				move();
			}
			turnAround();
			move();
			putBeeper();
			move();
		}
	}
	/*
	 * Karel will pick up the beepers from everywhere in its world except the midpoint.
	 * Pre:  Karel has placed beepers on every single spot of the first street except
	 * the midpoint.
	 * Post: Karel will have picked up all the beepers from its world. It will then move
	 * towards the midpoint and put a beeper there.
	 */
	private void pickBeepers() {
		while(frontIsClear()) {
			pickBeeper();
			move();
		}
		if(beepersPresent()) {
			pickBeeper();	//where it picks the beeper
		}
		turnAround();
		if(frontIsBlocked()) {	//checks for the 1x(any number) world
			putBeeper();
		}
		while(noBeepersPresent()) {
			if(frontIsClear()) {
				move();
			}
		}
	}

}