package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class OperadorError {


    private static final Logger log = LoggerFactory.getLogger(OperadorError.class);

    public void retry() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));
        personas.add(new Persona(1, "Luciano", 30));


        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("ERROR")))//simulamos un error , lanza un flujo con un error
                .retry(2)//va a ejecutar el codigo las veces que le pongas en los parentesis
                .doOnNext(x -> log.info(x.toString()))//para saber lo q pasa adentro
                .subscribe();


    }

    public void errorReturn() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));
        personas.add(new Persona(1, "Luciano", 30));


        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("HAY UN ERROR PAPA!!!!")))
                .onErrorReturn(new Persona(11, "COSO", 92))//le vamos a poner que queresmo que devolver cuando se ejecute un error
                .subscribe(x -> log.info(x.toString()));
    }


    public void errorResume() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));
        personas.add(new Persona(1, "Luciano", 30));


        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("HAY UN ERROR PAPA!!!!")))
                .onErrorResume(excepcion -> Mono.just(new Persona(11, "COSO", 92)))
                .subscribe(x -> log.info(x.toString()));
    }


    public void errorMap() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));
        personas.add(new Persona(1, "Luciano", 30));


        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("HAY UN ERROR PAPA!!!!")))
                .onErrorMap(excepcion -> new InstantiationException(excepcion.getMessage()))//como que
                .subscribe(x -> log.info(x.toString()));
    }
}