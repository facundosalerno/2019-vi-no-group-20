package server;

import controllers.ControllerGuardarropas;
import controllers.ControllerSesion;
import spark.Spark;
import spark.TemplateEngine;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {
    public static void main(String[] args) {
        //RepositorioGuardarropas.instance().findByUsuario(new Usuario());
        Spark.port(9010);
        Spark.staticFiles.location("/public");
        Spark.init();


        /**GLUE CODE*/
        TemplateEngine engine = new HandlebarsTemplateEngine();


        ControllerGuardarropas controllerGuardarropas =
                new ControllerGuardarropas();

        Spark.get("/guardarropa/prendas",
                controllerGuardarropas::mostrarPrendas,
                engine);

        ControllerSesion controllerSesion= new ControllerSesion();

        DebugScreen.enableDebugScreen();

    }

}