package tasks.sqlTasks;

import java.io.File;
import java.io.IOException;

/**
 * @author Tatevik Mirzoyan
 * Created on 16-Sep-20
 */
public class FileTask {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Tatev\\zoo.txt");
        System.out.println("File Exists: " + file.exists());
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Is Directory: " + file.isDirectory());
        System.out.println("Parent Path: " + file.getParent());
    }
}
