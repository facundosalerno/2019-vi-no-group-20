package server;

import domain.guardarropas.Guardarropas;
import domain.RepositorioGuardarropas;
import domain.usuario.Usuario;
import spark.ModelAndView;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {
    public static void main(String[] args) {
        //RepositorioGuardarropas.instance().findByUsuario(new Usuario());
        Spark.port(9010);
        Spark.staticFiles.location("/public");
        Spark.init();
        ControllerGuardarropas controller =
                new ControllerGuardarropas();

        Spark.get("/guardarropa/prendas",
                controller::prendas,
                new HandlebarsTemplateEngine());


        DebugScreen.enableDebugScreen();
    }

}