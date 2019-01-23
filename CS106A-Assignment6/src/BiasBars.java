/*
 * File: BiasBars.java
 * ---------------------
 * When it is finished, this program will implement the viewer for
 * the descriptor frequency data described in the assignment handout.
 */
/* Names: Romina Parimi
 * Section Leader: Belce Dogru
 * Purpose: The objective of this program is to produce a bar graph that shows
 * how descriptors are used in differing frequencies between men and women
 * across various social media platforms.
 * Citations: The Art and Science of Java, lecture slides
 */
import acm.program.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class BiasBars extends Program {

	/** The name of the file containing the data */
	private static final String DATA_FILE = "res/gender-data.txt";
	
	/** The width of the text field in the NORTH of the window */
	private static final int TEXT_FIELD_WIDTH = 16;

	private JLabel label;
	private JTextField text;
	private JButton graph;
	private JButton clear;
	
	private BiasBarsEntry entry;
	private BiasBarsGraph barGraph;
	/**
	 * This method has the responsibility for creating the database
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
		setTitle("BiasBars");
		label = new JLabel("Descriptor");
		text = new JTextField(TEXT_FIELD_WIDTH);
		graph = new JButton("Graph");
		clear = new JButton("Clear");
		
		barGraph = new BiasBarsGraph();
		
		text.addActionListener(this);
		text.setActionCommand("Graph");
		add(label, NORTH);
		add(text, NORTH);
		add(graph, NORTH);
		add(clear, NORTH);
		
		add(barGraph, CENTER);
		addActionListeners();
	}

	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Graph")) {
			
			entry = new BiasBarsEntry(getEntry());
			println("the entry via BiasBarsEntry is: " + entry.toString());
			
			barGraph.addEntry(entry);
			
		}
		if(e.getActionCommand().equals("Clear")) {
			println("Clear");
		}
	}
	
	/**
	 * This class will retrieve the entry from the data file that
	 * corresponds to the descriptor from the text field.
	 */
	private String getEntry() {
		try {
			Scanner s = new Scanner(new File(DATA_FILE));
			while(s.hasNextLine()) {
				if(s.next().equalsIgnoreCase(text.getText())) {
					String entry = text.getText() + s.nextLine();
					return entry;
				}
			}
		}
		catch(FileNotFoundException e) {
			String fileNotFound = "Error: File not found: " + e;
			return fileNotFound;
		}
		return null;
	}
	
}