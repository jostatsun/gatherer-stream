package com.atsunci.map;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Gatherer;
import java.util.stream.Gatherer.Integrator;

/**
 * @author Jost Adama
 * @version 1.0
 * @license ATSUN IT (<a href="https://www.atsunci.com">ATSUN IT</a>)
 * @email jost@atsunci.com
 * @since
 */
public class MapAvecGenerique {
    public static void main(String[] args) {
        var strings = new ArrayList<String>(List.of("Arnaud", "Benjamin", "Bernard", "Benjamin"));
        var list = strings.stream()
                .gather(mapGenerique(String::toUpperCase))
                .toList();
        System.out.println(list);
    }

    public static <T, R> Gatherer<T, ?, R> mapGenerique(
            Function<? super T, ? extends R> mapper) {
        Integrator<Void, T, R> integrator = (_, element, downstream) -> {
            R newElement = mapper.apply(element);
            return downstream.push(newElement);
        };
        return Gatherer.of(integrator);
    }
}
