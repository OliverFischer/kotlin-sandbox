package com.zsiegel.java.sandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsiegel
 */
public class Animals {

    private Animals() {
        super();
    }

    public static List<Animal> list() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Ant"));
        animals.add(new Animal("Koala"));
        animals.add(new Animal("Alpaca"));
        animals.add(new Animal("Marmot"));
        animals.add(new Animal("Dog"));
        animals.add(new Animal("Dragonfly"));
        animals.add(new Animal("Deer"));
        animals.add(new Animal("Fox"));
        animals.add(new Animal("Dolphin"));
        animals.add(new Animal("Dingo"));
        animals.add(new Animal("Mallard"));
        return animals;
    }
}
