package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Filtrado {
    private static final Logger log = LoggerFactory.getLogger(Filtrado.class);

    public void filter() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));

        Flux.fromIterable(personas)
                .filter(p -> p.getEdad() > 30)
                .subscribe(p -> log.info(p.toString()));
    }

    public void distinct() { //no duplicar resultados o que no se repitan resultados (tengo que hacer el equals y hascode)
        //le hice equals y hash a todos los parametros por eso tienen q ser los 3 paramentros iguales para q no se repitan,
        // si se repite uno nomas ya igual lo muestra en consola
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));
        personas.add(new Persona(1, "Luciano", 30));


        Flux.fromIterable(personas)
                .distinct()
                .subscribe(p -> log.info(p.toString()));
    }


    public void take() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));
        personas.add(new Persona(1, "Luciano", 30));


        Flux.fromIterable(personas)
                .take(2)// este agarra , toma los 2 primero elementos de una coleccion , tambien existe takeLast que agarra desde el ultimo hacia adelante.
                .subscribe(p -> log.info(p.toString()));

    }

    public void skip() {

        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));
        personas.add(new Persona(1, "Luciano", 30));


        Flux.fromIterable(personas)
                .skip(1)//aca saltea el primer elemento, o depende el numero q le pongas, tambien existe skipLast que hace lo mismo q el last del take
                .subscribe(p -> log.info(p.toString()));

    }
}
