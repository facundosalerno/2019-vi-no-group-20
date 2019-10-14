package controllers;

import domain.guardarropas.Guardarropas;
import domain.RepositorioGuardarropas;
import domain.guardarropas.GuardarropasLimitado;
import domain.guardarropas.GuardarropasPremium;
import domain.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerGuardarropas {

    public ModelAndView mostrarPrendas(Request req, Response res) {
        GuardarropasPremium guardarropas =
                RepositorioGuardarropas.instance()
                        .findByUsuario(new Usuario());

        return new ModelAndView(guardarropas, "guardarropas.hbs");
    }

}