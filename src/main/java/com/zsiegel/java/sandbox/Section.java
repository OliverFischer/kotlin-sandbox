package com.zsiegel.java.sandbox;

import java.util.Collection;

/**
 * @author zsiegel
 */
public class Section {
    public String title;
    public Collection<Animal> items;

    @Override
    public String toString() {
        return "Section: " + title + " items: " + items.toString();
    }
}
