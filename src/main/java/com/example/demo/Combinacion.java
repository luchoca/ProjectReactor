package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Combinacion {

    private static final Logger log = LoggerFactory.getLogger(Combinacion.class);

    // para combinar varios flujos de datos
    public void merge() {
        List<Persona> personas1 = new ArrayList<>();
        personas1.add(new Persona(1, "Luciano", 30));
        personas1.add(new Persona(2, "Castro", 31));
        personas1.add(new Persona(3, "Saad", 32));

        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(4, "Meli", 27));
        personas2.add(new Persona(5, "Caporale", 28));
        personas2.add(new Persona(6, "Zanonniani", 29));


        //le agregamos aca otra lista de venta que es otro tipo de flujo para probar que merge une diferentes flujos
        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas1);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

        Flux.merge(fx1, fx2, fx3)
                .subscribe(p -> log.info(p.toString()));

    }

    public void zip() {//combina
        List<Persona> personas1 = new ArrayList<>();
        personas1.add(new Persona(1, "Luciano", 30));
        personas1.add(new Persona(2, "Castro", 31));
        personas1.add(new Persona(3, "Saad", 32));

        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(4, "Meli", 27));
        personas2.add(new Persona(5, "Caporale", 28));
        personas2.add(new Persona(6, "Zanonniani", 29));


        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas1);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);


        //combina 2 flujos y tambien le puede operar con esa combinacion de los 2
//        Flux.zip(fx1, fx2, (p1, p2) -> String.format("Flux: %s, Flux2: %s", p1, p2))
//                .subscribe(x -> log.info(x));//ya es string por eso no va toString

        Flux.zip(fx1, fx2, fx3)
                .subscribe(x -> log.info(x.toString()));
    }

    public void zipWith() {//combina tambien pero le pones primero que flujo vas a combinar
        List<Persona> personas1 = new ArrayList<>();
        personas1.add(new Persona(1, "Luciano", 30));
        personas1.add(new Persona(2, "Castro", 31));
        personas1.add(new Persona(3, "Saad", 32));

        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(4, "Meli", 27));
        personas2.add(new Persona(5, "Caporale", 28));
        personas2.add(new Persona(6, "Zanonniani", 29));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas1);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);


        fx1.zipWith(fx2, (p1, p2) -> String.format("Flux: %s, Flux2: %s", p1, p2))
                .subscribe(x -> log.info(x));//ya es string por eso no va toString
    }
}