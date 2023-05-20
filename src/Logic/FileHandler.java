package Logic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
    public FileHandler (String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(1);

        }catch (IOException e) {
            System.out.println("File Not Found");
        }
    }
}
