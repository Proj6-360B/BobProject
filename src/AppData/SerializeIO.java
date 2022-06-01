package AppData;

import java.io.*;

public class SerializeIO {
    public static void serializeObjectToHere(Object theObject, String theFilePath) throws IOException {
        FileOutputStream fileOutStream = new FileOutputStream(theFilePath);
        ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
        objOutStream.writeObject(theObject);
        objOutStream.close();
        fileOutStream.close();
        System.out.println("Wrote " + theObject + " to " + theFilePath);
    }

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
