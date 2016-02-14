import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;


public class StringOperations{

  //Private members
  private HashMap <String, Integer> eleNames;
  private ArrayList<String> allMaxStrings;
  private int max;



  /**
  * Class constructor
  */
  public StringOperations() {
    super();
    this.eleNames = new HashMap <String, Integer> ();
    this.allMaxStrings = new ArrayList<String>();
    this.max = 0;
  }

  /**
  * Attempt to insert into the hashmap given a line.
  * Calls helper routines 'getElementName', and 'insertIntoHash'
  *
  * @param line a line from an xml file
  */
  public void attemptInsert(String line){
    String substring;

    //check if the current line has a name="whatever" element
    if((substring = this.getElementName(line)) != null){
      //If it does, simply insert into the hash and let
      //StringOp handle incrementing count
      this.insertIntoHash(substring);

    }
  }

  /**
  * Given a string, produce the substring of the element
  * name wherever a <xs:element name="whatever" is found
  *
  * @param line a string representing a line from the xml file
  * @return a string of either the element name if there is
  * one, or null if line does not have an element name.
  */
  public String getElementName(String line){
    int begin = 0, end=0;
    String ret = null;
    //Search for the element name
    if((begin = line.indexOf("<xs:element name=\"")) != -1){
      if((end = line.indexOf("\"", begin + 18)) != -1){
        //Obtain the substring
        ret = line.substring(begin + 18, end);
      }
    }
    return ret;
  }


  /**
  * Insert into the HashMap. Figures out the number of occurrences
  * and the updates the internal hashMap.
  * @param eleName an element name to insert into the hash map
  */
  public void insertIntoHash(String eleName){

    int occurrences = 1;
    if(this.eleNames.containsKey(eleName)){
      occurrences = this.eleNames.get(eleName) + 1;
    }
    //Insert
    this.eleNames.put(eleName, occurrences);

  }


  /**
  * Helper routine to find the element name that occurred
  * the most.
  * @return an int value representing the most occurrences
  */
  public void findMaxOccurrence(){

    int max = Integer.MIN_VALUE;

    for(Map.Entry<String, Integer> entry : this.eleNames.entrySet()){
      if(entry.getValue() > max){
        max = entry.getValue();
      }
    }
    this.max = max;
  }


  /**
  * After finding the most occurrences of any single element,
  * populate an array list with all the elements who share
  * that number of occurrences. Store results in internal arraylist.
  */
  public void getMaxStrings() {

    for(Map.Entry<String, Integer> entry : this.eleNames.entrySet()){
      if(entry.getValue() == this.max){
        this.allMaxStrings.add(entry.getKey());
      }
    }
  }

  /**
  * Create the output necessary, and print it.
  */
  public void printOutput(){
    StringBuilder sb = new StringBuilder();
    if(this.allMaxStrings.size() > 1){
       sb.append("The most frequently occurring elements are ");
      for(String name : this.allMaxStrings){
        sb.append(name + ", ");
      }
      sb.append("and they occur " + this.getMax() + " times.");
      //System.out.println(sb.toString());
    }
    else{
        sb.append("The most frequently occurring element is " +
        this.allMaxStrings.get(0) + ". It appears " +
        this.getMax() + " times.");
    }

    System.out.println(sb.toString());
  }

  /**
  * Get the max value.
  */
  public int getMax(){
    return this.max;
  }

  /**
  * For debugging purposes
  private HashMap <String, Integer> eleNames;
  private ArrayList<String> allMaxStrings;
  private int max;
  */
  @Override
  public String toString(){
    return "HashMap eleNames: " + this.eleNames.toString() +
    "\nArrayList allMaxStrings: " + this.allMaxStrings.toString() +
    "\nint max: " + this.max;

  }
}
