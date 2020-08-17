package lesson2.chapter3.bounds;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

public interface Flier {
    void fly();
}

class HangGlider implements Flier {
    @Override
    public void fly() {

    }

    private void anyFlier(List<Flier> fliers) {}
    private void groupOfFliers(List<? extends Flier> fliers) {}
}

class Goose implements Flier {
    @Override
    public void fly() {

    }
}

class Test {
    public static void addSound(List<? super String> list) {
        list.add(1,"1");
    }

    public static void main(String[] args) {
        List<? super IOException> exceptions = new ArrayList<Exception>();
//        exceptions.add(new Exception());
        exceptions.add(new IOException());
        exceptions.add(new FileNotFoundException());
        exceptions.add(new FileAlreadyExistsException(""));
    }

}