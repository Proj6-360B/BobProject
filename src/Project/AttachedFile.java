package Project;

import AppData.SerializeIO;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Stores a file for a Project by cloning it to appdata folder. <br>
 * AttachedFile saves a .fser in the corresponding Project folder, and clones the file to the /files
 * folder in the corresponding Project folder. <br>
 * Saves File location, name of file String, type String. <br>
 * Most methods require Project to pass in ("PATH + '/' + getFormattedName()") in order to save to
 * the correct Project folder.
 * @author David Huynh
 * @author Damien Cruz
 */
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
     * @author David Huynh
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

    /**
     * Get the File stored.
     * @author David Huynh
     * @return File stored.
     */
    public File getFile() {
        return file;
    }

    /**
     * Set a File to store.
     * @author David Huynh
     * @param file File to store.
     */
    public void setFile(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException(file + " is not a valid file.");
        }
        this.file = file;
    }

    /**
     * Get the name of the stored File.
     * @author David Huynh
     * @return Name of the stored File.
     */
    public String getName() {
        return name;
    }

    /**
     * (Helper, use rename() instead) Set the stored File's name.
     * @author David Huynh
     * @param name stored File's name to set.
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Set the stored File's name.
     * @author David Huynh
     * @param theProjectFolderPath pass in "PATH + '/' + getFormattedName()"
     * @param name Rename to this.
     * @throws IOException Failed to rename.
     */
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
        File oldS = new File(theProjectFolderPath + "/" + oldName + ".fser");
        if (oldS.exists()) {
            oldS.delete();
        }

        //Save new serialized
        writeFile(theProjectFolderPath);
    }

    /**
     * Get the stored File's type.
     * @author David Huynh
     * @return stored File's type.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the stored File's type.
     * @author David Huynh
     * @param type The type to set to.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Helper. Clones given File to appdata folder if it isn't already there.
     * @author David Huynh
     * @param theProjectFolderPath pass in "PATH + '/' + getFormattedName()"
     * @throws IOException File to copy over.
     */
    private void cloneFileToAppdata(String theProjectFolderPath) throws IOException {
        File f = new File(theProjectFolderPath + "/files/" + getName());
        if (file.getAbsolutePath().equals(f.getAbsolutePath())) return; //skip if already there

        FileUtils.copyFile(file, f);
        System.out.println("Writing: " + f.getAbsolutePath());
        file = f;
    }

    /**
     * Save this AttachFile's .fser and clone the File to appdata for storage.
     * @author David Huynh
     * @param theProjectFolderPath
     * @throws IOException
     */
    public void writeFile(String theProjectFolderPath) throws IOException {
        //itself
        SerializeIO.serializeObjectToHere(this, theProjectFolderPath + '\\' + getName() + ".fser");
        System.out.println("Writing: " + theProjectFolderPath + '\\' + getName() + ".fser");
        //cloneFileToAppdata
        cloneFileToAppdata(theProjectFolderPath);
    }

    /**
     * Delete this AttachFile, removing .fser and File clone.
     * @author David Huynh
     * @param theProjectFolderPath
     * @throws IOException
     */
    public void delete(String theProjectFolderPath) throws IOException {
        if (file.getAbsolutePath().contains("/appdata/"))
            file.delete(); //delete clone
        new File(theProjectFolderPath + '/' + getName() + ".fser").delete(); //delete serialize
    }
}
