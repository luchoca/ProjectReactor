package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Creacion {
    //creamos un log para adicionar algunas impresiones mas adelante
    private static final Logger log = LoggerFactory.getLogger(Creacion.class);

    public void justFrom() {

        //flujo con mono
        Mono.just(new Persona(17, "Lucho", 30));
        //flujo con Flux
//        Flux.fromIterable(aca va una coleccion);
    }

    public void empty() {

        //para manejar flujos vacios
        Mono.empty();
        Flux.empty();

    }


// para crear un flujo de datos a partir de un rango de numeros

    public void range() {
        Flux.range(0, 3)
                .doOnNext(i -> log.info("i : " + i))
                .subscribe();
    }

    //si se repiten flujos de datos
    public void repeat() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));
// este repite 3 veces toda la lista
        Flux.fromIterable(personas)
                .repeat(3)
                .subscribe(p -> log.info(p.toString()));
//este repite 3 veces pero solo la clase persona
        Mono.just(personas)
                .repeat(3)
                .subscribe(p -> log.info(p.toString()));
    }

}
