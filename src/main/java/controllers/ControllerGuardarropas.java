package controllers;

import cron.RepositorioGuardarropas;
import cron.RepositorioUsuarios;
import domain.guardarropas.GuardarropasPremium;
import domain.usuario.Usuario;
import exceptions.UsuarioInexistente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerGuardarropas {

    public ModelAndView mostrarGuardarropas(Request req, Response res) {
        String nombre= req.cookie("cookie_nombre");
        Usuario usuario;
        try{
            usuario = RepositorioUsuarios.getInstance().buscarUsuario(nombre);
        }catch (UsuarioInexistente e){
            return new ModelAndView(null, "forbidden.hbs");
        }

        return new ModelAndView(usuario, "guardarropas.hbs");
    }

}