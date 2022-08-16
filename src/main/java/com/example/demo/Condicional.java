package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Condicional {//operadores para cuando suceden eventos , tenemos q mostrar otros valores

    private static final Logger log = LoggerFactory.getLogger(Condicional.class);

    public void defaultIfEmpty() {// cuando algo devuelve un evento vacio , le ponemos info por defecto
//        Flux.empty() //sirve para flux o mono
        Mono.empty()
                .defaultIfEmpty(new Persona(11, "COSO POR DEFECTO", 92))
                .subscribe(x -> log.info(x.toString()));


    }


    public void takeUntill() {// mete flujos hasta q se cumpla lo que vos queres ahi en takeuntill
// se frena en el primero por q ya no cumple lo q pediamos ,
// si empezaramos de 30, 31 , 32 .
// se frena en 31 por q muestra el primero q cumple y muestra en el q se frena q seria 31.( va en orden de arriba para abajo)
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(2, "Castro", 33));
        personas.add(new Persona(3, "Saad", 40));
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));
        personas.add(new Persona(1, "Luciano", 29));


        Flux.fromIterable(personas)
                .takeUntil(p -> p.getEdad() > 30)
                .subscribe(x -> log.info(x.toString()));


    }

    public void timeout() throws InterruptedException {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(2, "Castro", 33));
        personas.add(new Persona(3, "Saad", 40));
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));
        personas.add(new Persona(1, "Luciano", 29));


        Flux.fromIterable(personas)
                .delayElements(Duration.ofSeconds(1)) //se va a demorar la lectura de los datos 1 segundos,
                // cada 1 segundo mete una lectura de la lista
                .timeout(Duration.ofSeconds(2))// osea que vamos a esperar 2 segundos para ejecutar el timeout,
                // si el time out demorara menos que el delay element, solo mostarria el time out y no mostraria nada la lista en consola
                .subscribe(x -> log.info(x.toString()));

        Thread.sleep(10000); //la app va a estar este tiempo "con vida" asi le da para leer los flujos y el time out
    }
}