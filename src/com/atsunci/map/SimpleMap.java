package com.atsunci.map;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Gatherer;

/**
 * @author Jost Adama
 * @version 1.0
 * @license ATSUN IT (<a href="https://www.atsunci.com">ATSUN IT</a>)
 * @email jost@atsunci.com
 * @since
 */
public class SimpleMap {
    public static void main(String[] args) {
        var list = new ArrayList<String>();
        list.add("Roland");
        list.add("Rodrigue");
        list.add("Arthur");
        list.add("Michael");

        list.stream()
                .gather(filtreGenerique(String::toUpperCase))
                .forEach(System.out::println);
    }

    public static Gatherer<String, ?, String> filtreGenerique(Function<String,String> functionMap) {
        Gatherer.Integrator<Void,String,String> integrator =  (_, element, downstream) -> {
            //Appliquons la transformation
            var valeurMapper = functionMap.apply(element);
            //Back propageons
            return downstream.push(valeurMapper);
        };
        return Gatherer.of(integrator);
    }

}
