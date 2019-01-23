/*
 * File: BiasBarsGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * frequencies is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the current entry changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;

public class BiasBarsGraph extends GCanvas implements ComponentListener {
	
	/** An array of the comment sources for the data. The order of the data in gender-data.txt will line up with this array. */	
	private static final String[] COMMENT_SOURCES = {"Facebook (pol.)", "Facebook (celeb.)", "TEDTalks", "Reddit", "Fitocracy"};

	/** The number of frequencies to label, from 0 to the maximum frequency shown */
	private static final int NLABELS = 11;

	/** The number to round the up to when choosing the maximum value shown on the y-axis */
	private static final int MAGNITUDE = 10;
	
	/** The total width of the bars for each comment source (the width of an actual bar will be half of this) */
	private static final int BARS_WIDTH = 150;

	/** The number of pixels to reserve at the top and bottom of the canvas */
	private static final int VERTICAL_MARGIN = 30;
	
	/** The number of pixels between the left side of the canvas and the left y-axis */
	private static final int LEFT_MARGIN = 50;
	
	/** The number of pixels between the right side of the canvas and the right y-axis */
	private static final int RIGHT_MARGIN = 30;
	
	/** The total length of each tick mark drawn on the y-axis */
	private static final int TICK_MARK_LENGTH = 8;
	
	/** The number of pixels between each axis and its labels */
	private static final int LABEL_OFFSET = 5;

	/** The maximum frequency on the y-axis when there is no current entry */
	private static final int DEFAULT_MAX_FREQ = 1000;
	
	/** The name of the file containing the data */
	private static final String DATA_FILE = "res/gender-data.txt";

	// TODO add instance variable(s)
	private GLabel firstLabel;
	private GLabel lastLabel;
	private GLabel xAxis;
	private GLine line;
	private BiasBarsDataBase file;
	private ArrayList<Integer> frequenciesWomen;
	private ArrayList<Integer> frequenciesMen;
	
	private int facebookcwomen;
	private int facebookpwomen;
	private int tedwomen;
	private int redwomen;
	private int fitwomen;
	
	private int facebookcmen;
	private int facebookpmen;
	private int tedmen;
	private int redmen;
	private int fitmen;
	
	private BiasBarsEntry entry;
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public BiasBarsGraph() {
		addComponentListener(this);
		frequenciesWomen = new ArrayList<Integer>();
		frequenciesMen = new ArrayList<Integer>();
		facebookcwomen = 0;
		facebookpwomen = 0;
		tedwomen = 0;
		redwomen = 0;
		fitwomen = 0;		
		facebookcmen = 0;
		facebookpmen = 0;
		tedmen = 0;
		redmen = 0;
		fitmen = 0;
	}
	
	
	/**
	* Clears the current entry stored inside this class. This method should 
	* not affect the appearance of the graph; that is done by calling update.
	*/
	public void clear() {
		// TODO implement this method
	}
	
	
	/**
	* Specifies a BiasBarsEntry to be the entry shown on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(BiasBarsEntry entry) {
		try {
			entry = new BiasBarsEntry(entry.toString());
			file = new BiasBarsDataBase(DATA_FILE);
			entry = file.findEntry(entry.getDescriptor());
			System.out.println("entry via BiasBarsGraph: " + entry);
			frequenciesWomen = entry.getFrequencies('W');
			System.out.println("women frequencies: " + frequenciesWomen);
			frequenciesMen = entry.getFrequencies('M');
			System.out.println("men frequencies: " + frequenciesMen);
			int x1 = 10;
			int xmove = LEFT_MARGIN*2;
			facebookcwomen = frequenciesWomen.get(0);
			GRect rect1 = new GRect(x1, facebookcwomen/10);
			add(rect1, xmove, 134);
			facebookpwomen = frequenciesWomen.get(1);
			GRect rect2 = new GRect(x1, facebookpwomen/10);
			xmove += 135;
			add(rect2, xmove, 134);
			tedwomen = frequenciesWomen.get(2);
			GRect rect3 = new GRect(x1, tedwomen/10);
			xmove += 135;
			add(rect3, xmove, 134);
			redwomen = frequenciesWomen.get(3);
			GRect rect4 = new GRect(x1, redwomen/10);
			xmove += 135;
			add(rect4, xmove, 134);
			fitwomen = frequenciesWomen.get(4);
			GRect rect5 = new GRect(x1, fitwomen/10);
			xmove += 135;
			add(rect5, xmove, 134);
			
			xmove = LEFT_MARGIN-30;
			facebookcmen = frequenciesMen.get(0);
			GRect rect6 = new GRect(x1, facebookcmen/10);
			xmove += 135;
			rect6.setFillColor(Color.RED);
			add(rect6, xmove, 134);
			facebookpmen = frequenciesMen.get(1);
			GRect rect7 = new GRect(x1, facebookpmen/10);
			xmove += 135;
			rect7.setFillColor(Color.RED);
			add(rect7, xmove, 134);
			tedmen = frequenciesMen.get(2);
			GRect rect8 = new GRect(x1, tedmen/10);
			xmove += 135;
			rect8.setFillColor(Color.RED);
			add(rect8, xmove, 134);
			redmen = frequenciesMen.get(3);
			GRect rect9 = new GRect(x1, redmen/10);
			xmove += 135;
			rect9.setFillColor(Color.RED);
			add(rect9, xmove, 134);
			fitmen = frequenciesMen.get(4);
			GRect rect10 = new GRect(x1, fitmen/10);
			xmove += 135;
			rect10.setFillColor(Color.RED);
			add(rect10, xmove, 134);
		}
		catch(NullPointerException e) {
			System.out.println("Error. File not found: " + e);
		}
	}

	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the current entry. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		GRect rect = new GRect(650+RIGHT_MARGIN, 200);
		int x = LEFT_MARGIN;
		int y = RIGHT_MARGIN;
		add(rect, x, y);	//graph
		if(entry != null) {
		int highestFreqLabel = entry.getMaxFreq() / MAGNITUDE * MAGNITUDE;
		if (highestFreqLabel != entry.getMaxFreq()) {
			highestFreqLabel += MAGNITUDE;
		}
		}
		lastLabel = new GLabel("" + DEFAULT_MAX_FREQ, x-(LEFT_MARGIN/1.25)-LABEL_OFFSET, y);
		lastLabel.setFont("*-BOLD-16");
		add(lastLabel);		//highest value on y axis
		for(int i = 0; i < NLABELS; i++) {
			line = new GLine(x-TICK_MARK_LENGTH, y, x+TICK_MARK_LENGTH, y);
			add(line);	//tick marks
			y += 20;
		}
		firstLabel = new GLabel("0", x-TICK_MARK_LENGTH-LABEL_OFFSET, y);
		firstLabel.setFont("*-BOLD-16");
		add(firstLabel);	//lowest value on y axis
		for(int i = 0; i < COMMENT_SOURCES.length; i++) {
			xAxis = new GLabel(COMMENT_SOURCES[i], LEFT_MARGIN+x, y);
			xAxis.setFont("*-BOLD-16");
			add(xAxis);		//x-axis labels
			x += 135;
		}
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}