package lesson9.nio2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.stream.Stream;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

public class FileAttributes {
    public static void main(String[] args) throws IOException {
        Files.isDirectory(Paths.get("/canine/coyote/fur.jpg"));
        Files.isRegularFile(Paths.get("/canine/types.txt"));
        Files.isSymbolicLink(Paths.get("/canine/coyote"));

//        out.println(Files.isHidden(Paths.get("/walrus.txt")));

        out.println(Files.isReadable(Paths.get("/seal/baby.png")));
        out.println(Files.isExecutable(Paths.get("/seal/baby.png")));

//        out.println(Files.size(Paths.get("/zoo/c/animals.txt")));

//        Path path = Paths.get("/rabbit/food.jpg");
//        out.println(Files.getLastModifiedTime(path).toMillis());
//        Files.setLastModifiedTime(path,
//                FileTime.fromMillis(currentTimeMillis()));
//        out.println(Files.getLastModifiedTime(path).toMillis());

        Path path1 = Paths.get("nums.txt");
        out.println(Files.getOwner(path1).getName());
        UserPrincipal owner = path1.getFileSystem()
                .getUserPrincipalLookupService().lookupPrincipalByName("Hrach-ПК\\Hrach");
        Files.setOwner(path1, owner);
        out.println(Files.getOwner(path1).getName());

        BasicFileAttributes data = Files.readAttributes(path1,
                BasicFileAttributes.class);
        out.println("Is path a directory? " + data.isDirectory());
        out.println("Is path a regular file? " + data.isRegularFile());
        out.println("Is path a symbolic link? " + data.isSymbolicLink());
        out.println("Path not a file, directory, nor symbolic link? " + data.isOther());
        out.println("Size (in bytes): " + data.size());
        out.println("Creation date/time: " + data.creationTime());
        out.println("Last modified date/time: " + data.lastModifiedTime());
        out.println("Last accessed date/time: " + data.lastAccessTime());
        out.println("Unique file identifier (if available): " + data.fileKey());
        out.println();

        Files.walk(Paths.get("."))
                .forEach(System.out::println);
        out.println();

        long dateFilter = 1420070400000l;

        Stream<Path> stream = Files.find(Paths.get("."), 10,
                (p, a) -> p.toString().endsWith(".java")
                        && a.lastModifiedTime().toMillis() > dateFilter);
        stream.forEach(out::println);

        Files.list(Paths.get("."))
                .filter(p -> !Files.isDirectory(p))
                .map(Path::toAbsolutePath)
                .forEach(System.out::println);

        Files.lines(Paths.get("nums.txt"),
                StandardCharsets.ISO_8859_1)
                .filter(s -> s.startsWith("WARN "))
                .map(s -> s.substring(5))
                .forEach(System.out::println);
    }
}
