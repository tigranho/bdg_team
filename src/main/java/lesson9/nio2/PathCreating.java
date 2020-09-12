package lesson9.nio2;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PathCreating {
    public static void main(String[] args) throws URISyntaxException {
        Path path = Paths.get("pandas/cuddly.png");
        Path path2 = Paths.get("c:\\zooinfo\\November\\employees.txt");
        Path path3 = Paths.get("/home/zoodirector");

        Path path01 = Paths.get("pandas", "cuddly.png");
        Path path02 = Paths.get("c:", "zooinfo", "November", "employees.txt");
        Path path03 = Paths.get("/", "home", "zoodirector");

        Path path001 = Paths.get(new URI("file://pandas/cuddly.png"));
        Path path002 = Paths.get(new URI("file:///c:/zoo-info/November/employees.txt"));
        Path path003 = Paths.get(new URI("file:///home/zoodirectory"));
        Path path4 = Paths.get(new URI("http://www.wiley.com"));
        Path path5 = Paths.get(new URI("ftp://username:password@ftp.the-ftp-server.com"));
        Path path6 = Paths.get(new URI("http://www.wiley.com"));
        URI uri4 = path4.toUri();

        Path path1 = FileSystems.getDefault().getPath("pandas/cuddly.png");
        Path path7 = FileSystems.getDefault().getPath("c:", "zooinfo", "November",
                "employees.txt");
        Path path8 = FileSystems.getDefault().getPath("/home/zoodirector");

        FileSystem fileSystem = FileSystems.getFileSystem(
                new URI("http://www.selikoff.net"));
        Path path9 = fileSystem.getPath("duck.txt");

        File file = new File("pandas/cuddly.png");
        Path path10 = file.toPath();
        file = path10.toFile();
    }
}
