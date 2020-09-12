package lesson9.nio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReading {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\новый_текстовой_документ.txt");
        try (BufferedReader reader = Files.newBufferedReader(path,
                StandardCharsets.US_ASCII)) {
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null)
                System.out.println(currentLine);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }

        Path path1 = Paths.get("gorilla.txt");
        List<String> data = new ArrayList();
        try (BufferedWriter writer = Files.newBufferedWriter(path1,
                StandardCharsets.UTF_16)) {
            writer.write("Hello World");
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
        System.out.println();

        Path path2 = Paths.get("nums.txt");
        try {
            final List<String> lines = Files.readAllLines(path2, StandardCharsets.ISO_8859_1);
            for(String line: lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
