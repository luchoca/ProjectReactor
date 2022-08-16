package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@SpringBootApplication
public class ProjectReactorMitoCodeApplication implements CommandLineRunner { //implemento commanlinerunner y me hace crear metodo dun

    private static final Logger log = LoggerFactory.getLogger(ProjectReactorMitoCodeApplication.class); //creo este Logger!

    //creamos metodos

    //    public void reactor() {
//        //creamos nuestro primer flujo
//        Mono.just(new Persona(1, "Luciano", 30))//sujeto en estudio , es ete flujo, debo subscribirme
//                .doOnNext(p -> log.info("[Reactor] Persona: " + p))//sirve para depurar datos
//                .subscribe(p -> log.info("[Reactor] Persona: " + p));//siempre hay q subscribirse para ver el flujo, recolectar los datos
//
//
//    }
//
// comento todo  para trabajar con mono y flux
//    public void mono() {
//        Mono.just(new Persona(1, "Luciano", 30))
//                .subscribe(p -> log.info(p.toString()));
//
//    }
//
//    public void flux (){
//        List<Persona> personas = new ArrayList<>();
//        personas.add(new Persona(1,"Luciano",30));
//        personas.add(new Persona(2,"Castro",31));
//        personas.add(new Persona(3,"Saad",32));
//
//        Flux.fromIterable(personas).subscribe(p->log.info(p.toString()));
//
//    }
//NO ANDA LA LIBRERIA RX
//    public void rxjava2() {
//        Observable.just(new Persona(1, "Luciano", 30))
//                    .doOnNext(p -> log.info("[Reactor] Persona: " + p))//sirve para depurar datos
//                .subscribe(p -> log.info("[RxJAva2] Persona: " + p));
//        ;
//    }
    public void fluxMono() {//transformacion de flux a mono
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Luciano", 30));
        personas.add(new Persona(2, "Castro", 31));
        personas.add(new Persona(3, "Saad", 32));

        Flux<Persona> fx = Flux.fromIterable(personas);
        fx.collectList().subscribe(lista -> log.info(lista.toString()));//en lugar de recorrer elemento por elemento vamos a tener un solo bloque que es la lista, o sea tenemos unos flux y lo transformamos en mono

    }


    public static void main(String[] args) {
        SpringApplication.run(ProjectReactorMitoCodeApplication.class, args);
    }

    @Override //metodo run q sale de implementar CommandLineRunner
    public void run(String... args) throws Exception {


//        mono();
//        flux();
//        reactor();
//        rxjava2();
//        fluxMono();

//        Creacion app = new Creacion();
////        app.range();
//        app.repeat();

//        Transformacion app = new Transformacion();
//        app.groupBy();

//        Filtrado app = new Filtrado();
////        app.distinct();
////        app.take();
//        app.skip();

        Combinacion app = new Combinacion();
        app.merge();
//        app.zipWith();

//        OperadorError app = new OperadorError();
////        app.retry();// el retry me va a devolver las veces q le puse ose 2 veces la lista y despues va a lanzar el error
////        app.errorReturn();//en cambio el error return ejecuta la lista 1 vez nomas y si hay error ya lanza el la persona por defecto que hay ahi
//        app.errorResume();

//        Condicional app = new Condicional();
////        app.defaultIfEmpty();
////        app.takeUntill();
//        app.timeout();

//        Matematicos app = new Matematicos();
////        app.average();
////        app.count();
////        app.min();
////        app.sum();
//        app.summarizing();

    }
}
