package Project;

import java.io.File;

public class AttachedFile {
    private File file;
    private String tagDescription;

    public AttachedFile(File file, String tagDescription) {
        this.file = file;
        this.tagDescription = tagDescription;
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

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }
}
