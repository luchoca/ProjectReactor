package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Matematicos {

    private static final Logger log = LoggerFactory.getLogger(Matematicos.class);

    public void average() {// promedio de un conjunto de datos
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));

        Flux.fromIterable(personas)
                .collect(Collectors.averagingInt(Persona::getEdad))
                .subscribe(p -> log.info(p.toString()));
    }


    public void count() {//cuenta los elementos que hay en el flujo
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));

        Flux.fromIterable(personas)
                .count()
                .subscribe(x -> log.info("Cantidad de Flujos : " + x));
    }

    public void min() { //encontar el valor minimo a traves de un flujo de datos
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));

        Flux.fromIterable(personas)
                .collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
                .subscribe(p -> log.info(p.get().toString()));

    }

    public void sum() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));

        Flux.fromIterable(personas)
                .collect(Collectors.summingInt(Persona::getEdad))
                .subscribe(x -> log.info("Suma : " + x));


    }

    public void summarizing() { // es como un resumen da varias opraciones aritmeticas como promedio , mayor, menor , etc

        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));

        Flux.fromIterable(personas)
                .collect(Collectors.summarizingInt(Persona::getEdad))
                .subscribe(x -> log.info("Resumen de operaciones para edad : " + x));
    }

}
