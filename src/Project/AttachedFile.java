package Project;

import AppData.SerializeIO;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

public class AttachedFile implements Serializable {
    /**
     * serialVersionUID so it doesnt auto generate and it farts during deserialize.
     */
    private static final long serialVersionUID = 91021L;
    /**
     * Path to file on disk.
     */
    private File file;
    /**
     * The name of the File on disk. (includes extension)
     */
    private String name;
    /**
     * The type (ie Manual, Receipt, etc.)
     */
    private String type;

    /**
     * Create new attached file (Name will be theFile's name).
     * @param theProjectFolderPath pass in "PATH + '/' + getFormattedName()"
     * @param theFile The Attached File original's place.
     * @param theType The type (ie Manual, Receipt, etc.)
     */
    public AttachedFile(String theProjectFolderPath, File theFile, String theType) throws IOException {
        setFile(theFile);
        setName(file.getName());
        setType(theType);
        writeFile(theProjectFolderPath);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException(file + " is not a valid file.");
        }
        this.file = file;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void rename(String theProjectFolderPath, String name) throws IOException {
        //check if exist & delete old copy
        String oldName = getName();
        File oldF = new File(theProjectFolderPath + "/files/" + getName());
        File newF = new File(theProjectFolderPath + "/files/" + name);
        if (oldF.exists()) {
//            FileUtils.copyFile(oldF, newF);
//            oldF.delete();
            oldF.renameTo(newF);
        }
        setFile(newF);
        setName(name);

        //Delete serialized
        File oldS = new File(theProjectFolderPath + "/" + oldName);
        if (oldS.exists()) {
            oldS.delete();
        }

        //Save new serialized
        writeFile(theProjectFolderPath);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private void cloneFileToAppdata(String theProjectFolderPath) throws IOException {
        File f = new File(theProjectFolderPath + "/files/" + getName());
        if (file.getAbsolutePath().equals(f.getAbsolutePath())) return; //skip if already there

        FileUtils.copyFile(file, f);
        System.out.println("Writing: " + f.getAbsolutePath());
        file = f;
    }

    public void writeFile(String theProjectFolderPath) throws IOException {
        //itself
        SerializeIO.serializeObjectToHere(this, theProjectFolderPath + '\\' + getName() + ".fser");
        System.out.println("Writing: " + theProjectFolderPath + '\\' + getName() + ".fser");
        //cloneFileToAppdata
        cloneFileToAppdata(theProjectFolderPath);
    }

    public void delete(String theProjectFolderPath) throws IOException {
        file.delete(); //delete clone
        new File(theProjectFolderPath + '/' + getName() + ".fser").delete(); //delete serialize
    }
}
