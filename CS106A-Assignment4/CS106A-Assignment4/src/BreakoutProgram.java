/*
 * CS 106A Breakout
 *
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! NOTE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * ! Your program should work properly with an UNMODIFIED version of this file.  !
 * ! You can and should modify the constants' values for testing, but you should !
 * ! not add any new constants here.  If you want additional constants for your  !
 * ! code, please add them to your own Breakout.java file, NOT here.             !
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *
 * This file contains several constant values that you should use in your
 * Breakout game code.  Your program should adapt properly if these constants
 * are changed to reasonable values.  You do not need to worry about your
 * program's behavior with strange values such as a negative brick size.
 * 
 * These constants are public so that they can be accessed in Breakout.java.
 *
 * This file and its contents are copyright (C) Stanford University and Marty Stepp,
 * licensed under Creative Commons Attribution 2.5 License.  All rights reserved.
 * 
 * @author Nick Troccoli, based on prior code from other Stanford lecturers
 * @version 2017/07/20
 * - initial version for 17sum; added VELOCITY_Y, minor renaming of some constants
 * @version 2017/04/30
 * - initial version for 17sp; minor renaming of some constants
 * @version 2016/04/27
 * - initial version (16sp)
 */
import java.awt.*;

public abstract class BreakoutProgram extends acm.program.GraphicsProgram {
	/**
	 * Dimensions of the canvas, in pixels
	 * These should be used when setting up the initial size of the game,
	 * but in later calculations you should use getWidth() and getHeight()
	 * rather than these constants for accurate size information.
	 */
	public static final double CANVAS_WIDTH = 420;
	public static final double CANVAS_HEIGHT = 600;

	// Stage 1: Set up the Bricks

	/**
	 * Number of bricks in each row
	 */
	public static final int NBRICK_COLUMNS = 10;

	/** 
	 * Number of rows of bricks
	 */
	public static final int NBRICK_ROWS = 10;

	/**
	 * Separation between neighboring bricks, in pixels
	 */
	public static final double BRICK_SEP = 4;

	/**
	 * Width of each brick, in pixels
	 */
	public static final double BRICK_WIDTH = Math.floor(
			(CANVAS_WIDTH - (NBRICK_COLUMNS + 1.0) * BRICK_SEP) / NBRICK_COLUMNS);

	/**
	 * Height of each brick, in pixels
	 */
	public static final double BRICK_HEIGHT = 8;

	/**
	 * Offset of the top brick row from the top, in pixels
	 */
	public static final double BRICK_Y_OFFSET = 70;

	
	// Stage 2: Create the Paddle

	/**
	 * Dimensions of the paddle
	 */
	public static final double PADDLE_WIDTH = 60;
	public static final double PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	public static final double PADDLE_Y_OFFSET = 30;
	
	
	// Stage 3: Create the Bouncing Ball
	
	/**
	 * Radius of the ball in pixels
	 */
	public static final double BALL_RADIUS = 10;

	/**
	 * The ball's vertical velocity.
	 */
	public static final double VELOCITY_Y = 3.0;
	
	/**
	 * The ball's minimum and maximum horizontal velocity; the bounds of the
	 * initial random velocity that you should choose (randomly +/-).
	 */
	public static final double VELOCITY_X_MIN = 1.0;
	public static final double VELOCITY_X_MAX = 3.0;
	
	/**
	 * Animation delay or pause time between ball moves (ms)
	 */ 
	public static final int DELAY = 1000 / 60;


	// Stage 4: Checking for Collisions (no new constants introduced)
	
	// Stage 5: Polish and Finishing Up

	/** Number of turns */
	public static final int NTURNS = 3;
	
	/** Font to use for on-screen text */
	public static final String SCREEN_FONT = "SansSerif-BOLD-18";
}

/*
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! NOTE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * ! Your program should work properly with an UNMODIFIED version of this file.  !
 * ! You can and should modify the constants' values for testing, but you should !
 * ! not add any new constants here.  If you want additional constants for your  !
 * ! code, please add them to your own Breakout.java file, NOT here.             !
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
