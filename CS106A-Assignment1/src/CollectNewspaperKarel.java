/* Name: Romina Parimi
 * Section Leader: Belce Dogru
 * Purpose: Karel's objective is to pick up the newspaper(represented by a beeper)
 * from outside the doorway(represented by walls) and return the newspaper and itself
 * back to its starting position.
 * Citations: Karel Coursereader
 */
import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	
	public void run() {
		moveToNewspaper();
		pickBeeper();
		returnToStart();
	}
	/*
	 * Karel will move towards the newspaper that is outside the doorway.
	 * Pre:  Karel is inside the doorway, facing east.
	 * Post: Karel will be facing east and outside the doorway on the space 
	 * that has the newspaper.
	 */
	private void moveToNewspaper() {
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}
	/*
	 * Karel is to return to its starting position.
	 * Pre:  Karel has picked up the newspaper(beeper), is on the space where
	 * the newspaper was, and is facing east.
	 * Post: Karel will be facing east at its starting position.
	 */
	private void returnToStart() {
		turnAround();
		move();
		turnRight();
		move();
		turnLeft();
		move();
		move();
		turnAround();
	}
	
}