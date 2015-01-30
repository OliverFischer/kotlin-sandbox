package com.zsiegel.java.sandbox;

/**
 * @author zsiegel
 */
public class Animal {
    public String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
