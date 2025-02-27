package com.atsunci.filtre;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Gatherer;

/**
 * @author Jost Adama
 * @version 1.0
 * @license ATSUN IT (<a href="https://www.atsunci.com">ATSUN IT</a>)
 * @email jost@atsunci.com
 * @since 27/02/2025
 */
public class Filtres {
    public static void main(String[] args) {
        //Création d'une liste de chaine de caractère
        var list = List.of("Jost","Emmanuel","James","Roland","Jane");
        //Création d'un filtre personnalisé avec les Gatherers
        list.stream()
                .gather(filtre(s -> s.startsWith("J")))
                .forEach(System.out::println);
    }

    private static Gatherer<String,?, String> filtre(Predicate<? super String> predicate) {
        Gatherer.Integrator<Void,String,String> integrator =
                (_, element, downstream) -> {
            if (predicate.test(element)) {
                return downstream.push(element);
            }
            return true;
        };
        return Gatherer.of(integrator);
    }
}
