package server;

import controllers.ControllerGuardarropas;
import controllers.ControllerPerfil;
import controllers.ControllerPrendas;
import controllers.ControllerSesion;
import spark.Spark;
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
        Spark.get("/perfil/guardarropas", controllerGuardarropas::mostrarGuardarropas, new HandlebarsTemplateEngine());

        ControllerSesion controllerSesion= new ControllerSesion();
        Spark.get("/login", controllerSesion::mostrarLogin, new HandlebarsTemplateEngine());
        Spark.post("/login", controllerSesion::crear, new HandlebarsTemplateEngine());
        Spark.post("/", controllerSesion::cerrarSesion, new HandlebarsTemplateEngine());

        ControllerPerfil controllerPerfil= new ControllerPerfil();
        Spark.get("/perfil", controllerPerfil::mostrar, new HandlebarsTemplateEngine());

        ControllerPrendas controllerPrendas = new ControllerPrendas();
        Spark.get("/perfil/guardarropas/prendas", controllerPrendas::mostrarPrendas, new HandlebarsTemplateEngine());

        DebugScreen.enableDebugScreen();

    }

}