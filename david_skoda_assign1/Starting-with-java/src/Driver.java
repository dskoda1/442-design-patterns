import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.lang.StringBuilder;



public class Driver {
  public static void main (String [] args) {

    //Error check the command line arguments
    if(args.length != 1){
      throw new IllegalArgumentException("\n\nYou need exactly one " +
      "argument for this program: an XML format file name.\n\n");
    }

    //Initialize FileProcessor, StringOperations instances
    FileProcessor fp = new FileProcessor(args[0]);
    StringOperations stringOp = new StringOperations();

    //Initialize some primitives
    String line = "";
    //While there is still lines in the file, read
    while((line = fp.readLineFromFile()) != null){

      stringOp.attemptInsert(line);

    }
    stringOp.findMaxOccurrence();
    stringOp.getMaxStrings();
    stringOp.printOutput();


  }
}
