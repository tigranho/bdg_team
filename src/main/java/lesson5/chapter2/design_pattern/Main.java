package lesson5.chapter2.design_pattern;

import lesson5.chapter2.design_pattern.builder.AnimalBuilder;
import lesson5.chapter2.design_pattern.immutable.Animal;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AnimalBuilder duckBuilder = new AnimalBuilder();
        duckBuilder
                .setAge(4)
                .setFavoriteFoods(Arrays.asList("grass", "fish")).setSpecies("duck");
        Animal duck = duckBuilder.build();
        Animal flamingo = new AnimalBuilder()
                .setFavoriteFoods(Arrays.asList("algae", "insects"))
                .setSpecies("flamingo").build();
    }
}
