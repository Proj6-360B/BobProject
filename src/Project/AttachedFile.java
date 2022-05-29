package Project;

import AppData.SerializeIO;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

public class AttachedFile implements Serializable {
    private File file;
    private String name;

    /**
     *
     * @param theProjectFolderPath pass in "PATH + '/' + getFormattedName()"
     * @param file The Attached File original's place.
     * @param name The name/description of the file.
     */
    public AttachedFile(String theProjectFolderPath, File file, String name) throws IOException {
        setFile(file);
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
            Files.copy(oldF, newF);
            oldF.delete();
        }
        setFile(newF);
        setName(name);
    }

    public void cloneFileToAppdata(String theProjectFolderPath) throws IOException {
        if (file.getPath().toLowerCase(Locale.ROOT).contains(theProjectFolderPath)) {
            System.out.println(file.getPath() + " does not need to be cloned.");
            return;
        }

        File f = new File(theProjectFolderPath + "/files/" + getName());
        if (f.exists()) f.delete();

        Files.copy(file, f);
        file = f;
    }

    public void writeFile(String theProjectFolderPath) throws IOException {
        //itself
        SerializeIO.serializeObjectToHere(this, theProjectFolderPath + '/' + getName() + ".fser");
        //cloneFileToAppdata
        cloneFileToAppdata(theProjectFolderPath);
    }
}
