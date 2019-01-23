/*
 * CS 106A Snowman
 *
 * DO NOT MODIFY THIS FILE!
 * DO NOT MODIFY THIS FILE!
 * DO NOT MODIFY THIS FILE!
 *
 * This file exists to force the student's program to have certain methods
 * that are required by the spec.
 * It is fine if your Snowman class has more methods
 * beyond the ones listed here.
 *
 * Author : Colin Kincaid
 * Version: 2018/07/11
 *
 * Your program should work properly with an UNMODIFIED version of this file.
 * If you want to modify this file for testing or for fun, that is your choice,
 * but when we grade your program we will do so with the original unmodified
 * version of this file, so your code must still work properly with that code.
 *
 * This file and its contents are copyright (C) Stanford University and Marty Stepp,
 * licensed under Creative Commons Attribution 2.5 License.  All rights reserved.
 */

import java.io.*;
import acm.program.*;

public abstract class SnowmanProgram extends ConsoleProgram {
	private static final String FONT = "Monospaced-Bold-14";
	
	/** drawing canvas used as second console */
	protected SnowmanCanvas canvas;
	
	/**
	 * Initializes the state of the Snowman program.
	 * Sets up the graphical canvas console and sets main console's font.
	 */
	public void init() {
		setFont(FONT);
		canvas = new SnowmanCanvas();
		add(canvas);
	}
	
	public void mergeConsoles() {
		canvas.merge(this);
	}
}

/*
 * DO NOT MODIFY THIS FILE!
 * DO NOT MODIFY THIS FILE!
 * DO NOT MODIFY THIS FILE!
 */

