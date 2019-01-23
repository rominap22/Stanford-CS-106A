/*
 * CS 106A Snowman
 *
 * DO NOT MODIFY THIS FILE!
 * DO NOT MODIFY THIS FILE!
 * DO NOT MODIFY THIS FILE!
 *
 * This instructor-provided file provides a simple graphical display of the
 * game state as a secondary console.
 *
 * Author : Colin Kincaid
 * Version: 2018/07/11
 *
 * Your program should work properly with an UNMODIFIED version of this file.
 * If you want to modify this file for testing or for fun, that is your choice.
 * Some students implement a fancier graphical display as an extra feature.
 *
 * This file and its contents are copyright (C) Stanford University and Marty Stepp,
 * licensed under Creative Commons Attribution 2.5 License.  All rights reserved.
 */

import acm.graphics.*;

public class SnowmanCanvas extends GCanvas {
	private static final String FONT = "Monospaced-Bold-13";
	private static final int LINE_HEIGHT = 14;
	
	private int y;   // current y-coordinate at which to draw line of text
	private SnowmanProgram program;   // program to which the canvas is attached
	
	/**
	 * Sets up initial state of canvas.
	 */
	public SnowmanCanvas() {
		y = LINE_HEIGHT;
	}
	
	/**
	 * Removes any drawn text from the canvas.
	 */
	public void clear() {
		removeAll();
		y = LINE_HEIGHT;
	}
	
	/**
	 * Causes all output sent to this console to instead go to the main console
	 * of the given Snowman program.
	 * @param snowmanProgram the snowman program whose console should be used
	 */
	public void merge(SnowmanProgram snowmanProgram) {
		this.program = snowmanProgram;
	}
	
	/**
	 * Prints the given text onto the console.
	 * @param text the text to print
	 */
	public void print(String text) {
		println(text);
	}
	
	/**
	 * Prints the given text onto the console.
	 * @param text the text to print
	 */
	public void println(String text) {
		if (program != null) {
			program.println(text);   // just forward to main console
		} else if (text.contains("\n")) {
			// special case: print a multi-line string
			String[] lines = text.split("\r?\n");
			for (String line : lines) {
				println(line);
			}
		} else {
			GLabel label = new GLabel(text, 0, y);
			label.setFont(FONT);
			add(label);
			y += LINE_HEIGHT;
		}
	}

	/**
	 * Ends merging of the console so that this canvas will once again
	 * display output on itself.
	 */
	public void unmerge() {
		program = null;
	}
}


/*
 * DO NOT MODIFY THIS FILE!
 * DO NOT MODIFY THIS FILE!
 * DO NOT MODIFY THIS FILE!
 */

