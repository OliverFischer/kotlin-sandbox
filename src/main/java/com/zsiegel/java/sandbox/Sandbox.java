package com.zsiegel.java.sandbox;

import rx.Observable;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author zsiegel
 */
public class Sandbox {

    public static void main(String[] args) {

        Observable.from(Animals.list()).toMultimap(new Func1<Animal, String>() {
            @Override
            public String call(Animal animal) {
                return animal.name.substring(0, 1);
            }
        }).flatMap(new Func1<Map<String, Collection<Animal>>, Observable<List<Section>>>() {
            @Override
            public Observable<List<Section>> call(Map<String, Collection<Animal>> stringCollectionMap) {

                List<Section> sections = new ArrayList<>();
                for (String sectionTitle : stringCollectionMap.keySet()) {
                    Section section = new Section();
                    section.title = sectionTitle;
                    section.items = stringCollectionMap.get(sectionTitle);
                    System.out.println(section);
                    sections.add(section);
                }

                return Observable.just(sections);
            }
        }).subscribe();

    }
}