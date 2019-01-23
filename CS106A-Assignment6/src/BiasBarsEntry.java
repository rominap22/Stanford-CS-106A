/*
 * File: BiasBarsEntry.java
 * --------------------------
 * This class represents a single entry in the database. Each
 * BiasBarsEntry contains a descriptor and two mappings from
 * comment source to number of occurrences of the descriptor, one
 * for women and one for men
 */

import acm.util.*;

import java.io.*;
import java.util.*;

public class BiasBarsEntry {

	private String entry;
	private char gender;
	private ArrayList<Integer> frequencies;
	private int maxFrequency;
	/**
	 * Creates a new BiasBarsEntry from a data line as it appears
	 * in the data file. Each line begins with the descriptor, which is
	 * followed by integers giving the occurrence of that descriptor in
	 * the RtGender database of social media comments. Before each list
	 * of integers is a letter "W" or "M" denoting which gender those
	 * frequencies correspond to
	 */
	public BiasBarsEntry(String line) {
		entry = line;
	}

	/**
	 * Returns the descriptor associated with this entry.
	 */
	public String getDescriptor() {
		try {
			Scanner s = new Scanner(entry);
			if(s.hasNext()) {
				String adj = s.next();
				return adj;
			}
		}
		catch(NullPointerException e) {
			String fileNotFound = "Error: File not found: " + e;
			return fileNotFound;
		}
		return null;
	}
	
	/**
	 * Returns the frequencies associated with an entry for a particular
	 * comment source and gender.
	 */
	public ArrayList<Integer> getFrequencies(char gender) {
		try {
			String current = "";
			frequencies = new ArrayList<Integer>();
			Scanner s = new Scanner(entry);
			current = s.next();	//skips the descriptor
			current = s.next();	//should be W
			if(gender == 'W') {
				this.gender = gender;
				while(s.hasNextInt()) {
					frequencies.add(s.nextInt());
				}
				return frequencies;
			} else if(gender == 'M') {
				this.gender = gender;
				while(s.hasNextInt()) {
					current = s.next();	//skips all previous women numbers
				}
				current = s.next();  //should be M
				while(s.hasNextInt()) {
					frequencies.add(s.nextInt());
				}
				return frequencies;
			}
		}
		catch(NullPointerException e) {
			return null;
		}
		return null;
	}

	/**
	 * Returns the highest frequency associated with a descriptor for any
	 * combination of comment source and gender to help with deciding the
	 * y-axis scale during graphing.
	 */
	public int getMaxFreq() {
		maxFrequency = 0;
		frequencies = getFrequencies('M');
		if(frequencies != null) {
			for(int i = 0; i < frequencies.size(); i++) {
				if(frequencies.get(i) > maxFrequency) {
					maxFrequency = frequencies.get(i);
				}
			}
		}
		frequencies = getFrequencies('W');
		if(frequencies != null) {
			for(int i = 0; i < frequencies.size(); i++) {
				if(frequencies.get(i) > maxFrequency) {
					maxFrequency = frequencies.get(i);
				}
			}
		}
		return maxFrequency;
	}

	/**
	 * Returns a string that makes it easy to see the value of a BiasBarsEntry.
	 */
	public String toString() {
		if(entry != null) {
			return "" + entry;
		}
		return null;
	}
}

