package server;

import controllers.ControllerGuardarropas;
import controllers.ControllerPerfil;
import controllers.ControllerSesion;
import spark.Spark;
import spark.TemplateEngine;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {
    public static void main(String[] args) {
        //RepositorioGuardarropas.instance().findByUsuario(new Usuario());
        Spark.port(9010);
        //Spark.staticFiles.location("/public");
        Spark.staticFileLocation("/public");
        Spark.init();


        /** GLUE CODE */
        //TemplateEngine engine = new HandlebarsTemplateEngine();

        ControllerGuardarropas controllerGuardarropas = new ControllerGuardarropas();
        Spark.get("/guardarropa/prendas", controllerGuardarropas::mostrarPrendas, new HandlebarsTemplateEngine());

        ControllerSesion controllerSesion= new ControllerSesion();
        Spark.get("/login", controllerSesion::mostrarLogin, new HandlebarsTemplateEngine());
        Spark.post("/login", controllerSesion::crear, new HandlebarsTemplateEngine());

        ControllerPerfil controllerPerfil= new ControllerPerfil();
        Spark.get("/usuarios/:id", controllerPerfil::mostrar, new HandlebarsTemplateEngine());

        DebugScreen.enableDebugScreen();

    }

}