package controllers;

import cron.RepositorioGuardarropas;
import cron.RepositorioUsuarios;
import domain.guardarropas.Guardarropas;
import domain.usuario.Usuario;
import exceptions.NoExisteGuardarropasException;
import exceptions.UsuarioInexistente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerPrendas {
    public ModelAndView mostrarPrendas(Request req, Response res) {
        String nombreGuardarropas = req.cookie("cookie_nombreGuardarropas");
        Guardarropas guardarropas;
        try{
            guardarropas = RepositorioGuardarropas.getInstance().buscarGuardarropas(nombreGuardarropas);

        }catch (NoExisteGuardarropasException e){
            return new ModelAndView(null, "forbidden.hbs");
        }
        return new ModelAndView(guardarropas, "prendas.hbs");
    }

    public ModelAndView crearPrenda(Request req, Response res){

        return new ModelAndView(null, "crearPrenda.hbs");
    }
}
