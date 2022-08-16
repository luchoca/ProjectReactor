package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Transformacion {// metodos de transformacion

    private static final Logger log = LoggerFactory.getLogger(Transformacion.class);

    public void map() {
//        List<Persona> personas = new ArrayList<>();
//        personas.add(new Persona(1, "Luciano", 30));
//        personas.add(new Persona(2, "Castro", 31));
//        personas.add(new Persona(3, "Saad", 32));
//
//        //como defino mas de una linea detro del map abro {} sino seria solo con ()
//        //con el map transformamos los elementos que vienen en el flujo,
//        //para cada elemento p (que son las personas) voy a ejecutar -> una funcionalidad
//        Flux.fromIterable(personas).map(p -> {
//            p.setEdad(p.getEdad() + 10);
//            return p;
//        }).subscribe(p -> log.info(p.toString()));


        //crea una lista del 0 al 9 y para transformarlo y sumarle 10 mas a cada
        // uno tiene q crear ese Flux<Interger> por que sino el map no transforma monos o algo asi

        Flux<Integer> fx = Flux.range(0, 10);
        Flux<Integer> fx2 = fx.map(x -> x + 10);
        fx2.subscribe(x -> log.info("X : " + x));
    }


    public void flatMap() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));

        Flux.fromIterable(personas)
                .flatMap(p -> {
                    p.setEdad(p.getEdad() + 10);
                    return Mono.just(p);
                })
                .subscribe(p -> log.info(p.toString()));

    }

    public void groupBy() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(1, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));

        Flux.fromIterable(personas)
                .groupBy(Persona::getIdPersona)// group by agrupa por id y los :: es lo mismo que poner (p->p.getIdPersona)
                .flatMap(idFlux -> idFlux.collectList())
                .subscribe(x -> log.info(x.toString()));

    }
}
