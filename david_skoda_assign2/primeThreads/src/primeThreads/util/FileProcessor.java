package primeThreads.util;

import java.lang.System;

//File input classes, exceptions
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public final class FileProcessor{

	//Private members.
	private String fileName;
	private BufferedReader br;

	/**
	 * Class constructor.
	 * Exits the program if not passed a file name.
	 */
	public FileProcessor() {
		super();
		System.err.println("You need to pass a string file name in order to "
				+ "use this class.");
		System.exit(1);
	}
	/**
	 * Class constructor.
	 * Constructs the BufferedReader after opening the correct file that
	 * will be used in later file I/O operations.
	 * @param fileName a String the designates some xml file to be read.
	 *
	 * Use of a FileInputStream, InputStreamReader, and BufferedReader
	 * was borrowed from http://www.programcreek.com/2011/03/java-read-
	 * a-file-line-by-line-code-example/
	 */
	public FileProcessor(String fileName) {
		super();
		Logger.writeMessage("Constructor for FileProcessor Class called.",
				Logger.DebugLevel.CONSTRUCTOR);	
		this.fileName = fileName;
		//Construct the infrastructure to read the file
		try{
			FileInputStream fis = new FileInputStream(fileName);
			//Save this BufferedReader for use to read from file
			this.br = new BufferedReader(new InputStreamReader(fis));
		}catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
			System.out.println("File Not Found Exception: "
					+ fnfe.getCause());
			System.out.println("File name of: " + fileName +
					" was unable to be found/opened. Please check your filename.");
			System.exit(1);
		}
	}
	/**
	 * Reads a single line from the BufferedReader class member.
	 * @return A line from the file, String
	 */
	public synchronized String readLineFromFile(){
		String line = null;
		try{
			//Attempt to read a line, and close if no more lines
			if(this.br.ready()){
				if((line = this.br.readLine()) == null){
					this.br.close();
				}
			}
		}catch(IOException io){
			io.printStackTrace();
			System.out.println("IOException raised when reading a line "
					+ io.getCause());
		}
		return line;
	}

	/**
	 * For debugging purposes
	 */
	@Override
		public String toString(){
			return "BufferedReader br: " + this.br.toString() +
				"\nString fileName: " + this.fileName;

		}

}
