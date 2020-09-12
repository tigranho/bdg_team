package lesson9.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.out;

public class FilesMethods {
    public static void main(String[] args) throws IOException {
        out.println(Files.exists(Paths.get("C:\\Users\\Hrach\\IdeaProjects")));
        out.println(Files.exists(Paths.get("Users\\Hrach\\IdeaProjects")));

        Path path = Paths.get("C:\\Users\\Hrach\\IdeaProjects\\ddd.exe");
        Path path1 = Paths.get("C:\\Users\\Hrach\\IdeaProjects\\ddd.exe");
        out.println(Files.isSameFile(path, path1));

        Files.createDirectory(Paths.get("bison"));
        Files.createDirectories(Paths.get("bison/field/pasture/green"));


        Files.copy(Paths.get("src/main/java/lesson1/"),
                Paths.get("bison/field/pasture/green/test"));
        Files.copy(Paths.get("nums.txt"),
                Paths.get("src/main/java/lesson1/nums.txt"));

        Files.move(Paths.get("c:\\zoo"), Paths.get("c:\\zoo-new"));
        Files.move(Paths.get("c:\\user\\addresses.txt"),
                Paths.get("c:\\zoo-new\\addresses.txt"));

        Files.delete(Paths.get("/vulture/feathers.txt"));
        Files.deleteIfExists(Paths.get("/pigeon"));
    }
}
