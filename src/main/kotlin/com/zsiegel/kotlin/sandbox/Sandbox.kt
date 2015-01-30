package com.zsiegel.kotlin.sandbox

import rx.Observable
import java.util.ArrayList
import com.zsiegel.java.sandbox.Animals
import com.zsiegel.java.sandbox.Section
import com.zsiegel.java.sandbox.Animal

/**
 * @author zsiegel
 */
fun main(args: Array<String>) {
    
    //Standard function call
    var animalsBySection = mapAnimalsByName();
    
    //Extension function tacked on to any instance of List<Animal>
    //var animalsBySection = Animals.list().mapByName();
    
    println(animalsBySection)
}

/**
 * An extension method available on any instance of List<Animal>
 */
fun List<Animal>.mapByName() : List<Section> {
    return mapAnimalsByName();
}

/**
 * Take a list of animals and output a list of
 * sections containing the animals keyed by name
 */
fun mapAnimalsByName() : List<Section> {
    return Observable.from(Animals.list())
            .toMultimap { animal ->
                animal.name.substring(0, 1);
            }.flatMap { mapOfAnimals ->
        
                val sections = ArrayList<Section>();
                for (sectionTitle in mapOfAnimals.keySet()) {
                    val section = Section()
                    section.title = sectionTitle
                    section.items = mapOfAnimals.get(sectionTitle)
                    sections.add(section)
                }

                Observable.just(sections)
            }.toBlocking().first();
}
