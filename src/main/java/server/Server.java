package server;

import controllers.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.after;

public class Server {
    public static void main(String[] args) {
        Spark.port(9010);
        //Spark.staticFiles.location("/public");
        Spark.staticFileLocation("/public");
        Spark.init();


        /** GLUE CODE */

        ControllerSesion controllerSesion= new ControllerSesion();
        Spark.get("/login", controllerSesion::mostrarLogin, new HandlebarsTemplateEngine());
        Spark.post("/login", controllerSesion::iniciarSesion, new HandlebarsTemplateEngine());
        Spark.post("/", controllerSesion::cerrarSesion, new HandlebarsTemplateEngine());

        ControllerPerfil controllerPerfil= new ControllerPerfil();
        Spark.get("/perfil", controllerPerfil::mostrar, new HandlebarsTemplateEngine());

        ControllerGuardarropas controllerGuardarropas = new ControllerGuardarropas();
        Spark.get("/guardarropas", controllerGuardarropas::mostrarGuardarropas, new HandlebarsTemplateEngine());
        /* POST /guardarropas para crear guardarropas */

        ControllerPrendas controllerPrendas = new ControllerPrendas();
        Spark.get("/guardarropas/:nombre/prendas", controllerPrendas::mostrarPrendas,  new HandlebarsTemplateEngine());
        Spark.get("/guardarropas/:nombre/prendas/wizard", controllerPrendas::creacionPrenda,  new HandlebarsTemplateEngine());
        Spark.post("/guardarropas/:nombre/prendas", controllerPrendas::crearPrenda, new HandlebarsTemplateEngine());

        ControllerCalendario controllerCalendario = new ControllerCalendario();
        Spark.get("/calendario", controllerCalendario::mostrar, new HandlebarsTemplateEngine());

        DebugScreen.enableDebugScreen();


        //TODO: OJO, VER AL MOMENTO DE ver tema TRANSACCIONES

        /*after((request,response) -> {
            PerThreadEntityManagers.getEntityManager();
            PerThreadEntityManagers.closeEntityManager();
        });*/


    }

}