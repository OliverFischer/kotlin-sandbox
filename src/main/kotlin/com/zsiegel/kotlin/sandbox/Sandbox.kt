package com.zsiegel.kotlin.sandbox

import rx.Observable
import java.util.ArrayList
import com.zsiegel.java.sandbox.Animals
import com.zsiegel.java.sandbox.Section

/**
 * @author zsiegel
 */
fun main(args: Array<String>) {

    Observable.from(Animals.list())
            .toMultimap { animal ->
                animal.name.substring(0, 1);
            }.flatMap { mapOfAnimals ->
                val sections = ArrayList<Section>();
                    for (sectionTitle in mapOfAnimals.keySet()) {
                    val section = Section()
                    section.title = sectionTitle
                    section.items = mapOfAnimals.get(sectionTitle)
                    println(section)
                    sections.add(section)
                }
                Observable.just(sections)
            }.subscribe();

}
