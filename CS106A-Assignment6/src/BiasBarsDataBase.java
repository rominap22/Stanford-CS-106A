/*
 * File: BiasBarsDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of descriptors.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * descriptor and get back the corresponding BiasBarsEntry.
 * Descriptors are matched independent of case, so that "nice"
 * and "NICE" are the same word.
 */

import acm.util.*;
import java.util.*;
import java.io.*;

public class BiasBarsDataBase {

	private String file;
	
	
	/**
	 * Constructor: BiasBarsDataBase(filename)
	 * Creates a new BiasBarsDataBase and initializes it using the
	 * data in the specified file. The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public BiasBarsDataBase(String filename) {
		try {
			file = filename;
		}
		catch(NullPointerException e) {
			System.out.println("Error. File not found: " + e);
		}
	}

	/**
	 * Returns the BiasBarsEntry associated with this descriptor,
	 * if one exists. If the descriptor does not appear in the database, this
	 * method returns null.
	 */
	public BiasBarsEntry findEntry(String descriptor) {
		try {
			Scanner s = new Scanner(new File(file));
			String line = "";
			while(s.hasNextLine()) {
				if(s.next().equalsIgnoreCase(descriptor)) {
					line = descriptor + s.nextLine();
					BiasBarsEntry entry = new BiasBarsEntry(line);
					return entry;
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Error. File not found: " + e);
		}
		return null;
	}
	
}