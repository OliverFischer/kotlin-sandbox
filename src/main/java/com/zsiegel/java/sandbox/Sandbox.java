package com.zsiegel.java.sandbox;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author zsiegel
 */
public class Sandbox {

    public static void main(String[] args) {
        List<Section> animalsBySection = mapAnimalsByName(Animals.list());
        System.out.println(animalsBySection);
        
        //This wont compile on 7 - but will compile on 8
        Observable.from(Animals.list()).compose(applySchedulers()).subscribe(new Subscriber<Animal>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Animal animal) {

            }
        });
    }

    /**
     * Take a list of animals and output a list of
     * sections containing the animals keyed by name
     */
    private static List<Section> mapAnimalsByName(List<Animal> animals) {
        return Observable.from(animals)
                .toMultimap(new Func1<Animal, String>() {
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
                            sections.add(section);
                        }

                        return Observable.just(sections);
                    }
                }).toBlocking().first();
    }

    private static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.immediate());
            }
        };
    }
}
