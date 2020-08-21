package lesson3.chapter2.design_pattern.interfaces;

import java.util.List;

public class DummyRaceManager implements RaceManager {
    @Override
    public Animal getWinner(List<Animal> animals) {
        return animals == null || animals.isEmpty() ? null : animals.get(0);
    }
}
