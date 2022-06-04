package AppData;

import java.io.*;

/**
 * Static class to write whatever object as serialized.
 * @author David Huynh
 * @author Damien Cruz
 */
public class SerializeIO {
    /**
     * Serialize given Object to given output path.
     * @author David Huynh
     * @param theObject to Serialize.
     * @param theFilePath to save to.
     * @throws IOException
     */
    public static void serializeObjectToHere(Object theObject, String theFilePath) throws IOException {
        FileOutputStream fileOutStream = new FileOutputStream(theFilePath);
        ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
        objOutStream.writeObject(theObject);
        objOutStream.close();
        fileOutStream.close();
        System.out.println("Wrote " + theObject + " to " + theFilePath);
    }

    /**
     * Deserialize and return the Object from given path.
     * @author David Huynh
     * @param theFilePath to Deserialize.
     * @return Deserialized Object. (typecast it)
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserializeObjectFromHere(String theFilePath) throws IOException, ClassNotFoundException {
        FileInputStream fileInStream = new FileInputStream(theFilePath);
        ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
        Object result = objInStream.readObject();
        fileInStream.close();
        objInStream.close();
        System.out.println("Read " + result + " from " + theFilePath);
        return result;
    }
}
