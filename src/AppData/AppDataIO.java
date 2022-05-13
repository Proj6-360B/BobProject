package AppData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * https://www.baeldung.com/java-compress-and-uncompress
 * https://www.tutorialspoint.com/javazip/javazip_zipoutputstream_write.htm
 * @author David Huynh
 */
public class AppDataIO {
    private static String PATH_APPDATA = "/appdata/";

    public static void main(String[] args) {
        try {
            FileOutputStream fout = new FileOutputStream("appdata.zip");
            CheckedOutputStream checksum = new CheckedOutputStream(fout, new Adler32());
            ZipOutputStream zout = new ZipOutputStream(checksum);

            FileInputStream fin = new FileInputStream(PATH_APPDATA);
            ZipEntry zipEntry = new ZipEntry(PATH_APPDATA);
            zout.putNextEntry(zipEntry);
            int length;
            byte[] buffer = new byte[1024];
            while((length = fin.read(buffer)) > 0) {
                zout.write(buffer, 0, length);
            }

            zout.closeEntry();
            zout.finish();
            fin.close();
            zout.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

