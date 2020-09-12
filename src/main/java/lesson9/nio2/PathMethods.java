package lesson9.nio2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.out;

public class PathMethods {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Users\\Hrach\\IdeaProjects");
        out.println("The Path Name is: " + path);
        for (int i = 0; i < path.getNameCount(); i++) {
            out.println(" Element " + i + " is: " + path.getName(i));
        }
        printPathInformation(path);

        Path path1 = Paths.get("C:\\birds\\egret.txt");
        out.println("Path1 is Absolute? " + path1.isAbsolute());
        out.println("Absolute Path1: " + path1.toAbsolutePath());
        Path path2 = Paths.get("birds/condor.txt");
        out.println("Path2 is Absolute? " + path2.isAbsolute());
        out.println("Absolute Path2 " + path2.toAbsolutePath());

        Path path3 = Paths.get("/mammal/carnivore/raccoon.image");
        out.println("Path is: " + path3);
        out.println("Subpath from 0 to 3 is: " + path3.subpath(0, 3));
        out.println("Subpath from 1 to 3 is: " + path3.subpath(1, 3));
        out.println("Subpath from 1 to 2 is: " + path3.subpath(1, 2));

        Path path4 = Paths.get("fish.txt");
        Path path5 = Paths.get("birds.txt");
        out.println(path4.relativize(path5));
        out.println(path5.relativize(path4));

        final Path path6 = Paths.get("/cats/../panther");
        final Path path7 = Paths.get("food");
        System.out.println(path6.resolve(path7));

        final Path path8 = Paths.get("/turkey/food");
        final Path path9 = Paths.get("/tiger/cage");
        System.out.println(path8.resolve(path9));

        Path path10 = Paths.get("E:\\data");
        Path path11 = Paths.get("E:\\user\\home");
        Path relativePath = path10.relativize(path11);
        System.out.println(path10.resolve(relativePath));
        System.out.println(path10.resolve(relativePath).normalize());

        out.println(Paths.get(".").toRealPath());
    }

    public static void printPathInformation(Path path) {
        out.println("Filename is: " + path.getFileName());
        out.println("Root is: " + path.getRoot());
        Path currentParent = path;
        while ((currentParent = currentParent.getParent()) != null) {
            out.println(" Current parent is: " + currentParent);
        }
    }
}
