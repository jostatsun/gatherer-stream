package com.atsunci.filtre;

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
public class FiltresGeneriques {
    public static void main(String[] args) {
        var maList = List.of("Jost", "Emmanuel", "James", "Roland", "Jane");

        var list = maList.stream()
                .gather(
                        filtreGenerique(
                            Predicate.not(s -> s.startsWith("J"))
                        )
                )
                .toList();

        System.out.println(list); // [Emmanuel, Roland]
    }

    private static <T> Gatherer<T, ?, T> filtreGenerique(Predicate<T> predicateFiltre) {
        return Gatherer.of(
                (_, element, downstream) -> {
                    boolean test = predicateFiltre.test(element);
                    if (test) {
                        return downstream.push(element);
                    }
                    return true;
                }
        );
    }
}
