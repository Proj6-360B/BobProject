package Project;

import AppData.SerializeIO;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

public class AttachedFile implements Serializable {
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
        cloneFileToAppdata(theProjectFolderPath);
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
        //check if exist & delete old
        File oldF = new File(theProjectFolderPath + "/files/" + getName());
        File newF = new File(theProjectFolderPath + "/files/" + name);
        if (oldF.exists()) {
            FileUtils.copyFile(oldF, newF);
            oldF.delete();
        }
        setFile(newF);
        setName(name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void cloneFileToAppdata(String theProjectFolderPath) throws IOException {
        File f = new File(theProjectFolderPath + "/files/" + getName());
        if (file.getAbsolutePath().equals(f.getAbsolutePath())) return; //skip if already there

        FileUtils.copyFile(file, f);
        file = f;
    }

    public void writeFile(String theProjectFolderPath) throws IOException {
        //itself
        SerializeIO.serializeObjectToHere(this, theProjectFolderPath + '\\' + getName() + ".fser");
        //cloneFileToAppdata
        cloneFileToAppdata(theProjectFolderPath);
    }

    public void delete(String theProjectFolderPath) throws IOException { //TODO test
        file.delete(); //delete clone
        new File(theProjectFolderPath + "/files/" + getName()); //delete serialize
    }
}
