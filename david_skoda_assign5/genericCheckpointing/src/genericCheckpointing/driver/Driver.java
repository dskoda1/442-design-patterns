
package genericCheckpointing.driver;

import genericCheckpointing.util.ProxyCreator;

// import the other types used in this file

public class Driver {

  public static void main(String[] args) {

    // FIXME: read the value of checkpointFile from the command line

    ProxyCreator pc = new ProxyCreator();

    // create an instance of StoreRestoreHandler (which implements
    // the InvocationHandler

    // create a proxy
    StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
        new Class[] {
        StoreI.class, RestoreI.class
        }, 
        new StoreRestoreHandler()
        );

    // FIXME: invoke a method on the handler instance to set the file name for checkpointFile and open the file

    MyAllTypesFirst myFirst;
    MyAllTypesSecond  mySecond;

    // create a vector to store the objects being serialized
    for (int i=0; i<NUM_OF_OBJECTS; i++) {
      // FIXME: create these object instances correctly.
      myFirst = new MyAllTypesFirst(...);
      mySecond = new MyAllTypesSecond(..);

      // FIXME: store myFirst and mySecond in the vector 
      ((StoreI) cpointRef).writeObj(myFirst, "XML");
      ((StoreI) cpointRef).writeObj(mySecond, "XML");

    }

    SerializableObject myRecordRet;

    // create a vector to store the returned ojects
    for (int j=0; j<2*NUM_OF_OBJECTS; j++) {

      myRecordRet = ((RestoreI) cpointRef).readObj("XML");
      // FIXME: store myRecordRet in the vector
    }

    // FIXME: invoke a method on the handler to close the file (if it hasn't already been closed

    // FIXME: compare and confirm that the serialized and deserialzed objects are equal

  }
}
