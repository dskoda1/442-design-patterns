package genericCheckpointing.util;

import java.lang.System;

//File input classes, exceptions
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public final class FileProcessor implements FileProcessorI{

  //Private members.
  private String fileName;
  private BufferedReader br;
  private BufferedWriter bw;

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
  public FileProcessor(String fileName, boolean forOutput){
		try {
			bw = new BufferedWriter(new FileWriter(fileName, false));
		} catch (IOException e) {
			System.out.println("Output file titled '" + fileName + "' unable to be opened for write.");
			e.printStackTrace();
			System.exit(1);
		}

  }
  /**
   * Reads a single line from the BufferedReader class member.
   * @return A line from the file, String
   */
  public String readLine(){
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
   * Write a single line to the BufferedWriter class member.
   */
  public void writeLine(String line){
	  try {
		bw.write(line);
		bw.newLine();
	} catch (IOException e) {
		System.out.println("Error writing line to file " + fileName);
		e.printStackTrace();
		System.exit(1);
	}
  }
  
  public void close(){
	  if(bw != null){
		  try {
			bw.close();
		} catch (IOException e) {
			System.out.println("Exception closing buffered writer for file: " + fileName);
			e.printStackTrace();
		}
	  }
	  if(br != null){
		  try{
			  br.close();
		  }catch(IOException io){
			  System.out.println("Exception closing buffered reader for file: " + fileName);
				io.printStackTrace();		  
		  }
	  }
  }
  
  /**
   * For debugging purposes
   */
  @Override public String toString(){
    return "BufferedReader br: " + this.br.toString() +
      "\nString fileName: " + this.fileName;

  }

}