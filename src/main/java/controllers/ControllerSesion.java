package controllers;

import cron.RepositorioUsuarios;
import domain.usuario.*;
import exceptions.ContraseñaInvalidaException;
import exceptions.UsuarioInexistente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class ControllerSesion {


    // .../guardarropas/:id/prendas

    // .../guardarropas/prendas

    public ModelAndView mostrarLogin(Request req, Response res) {
        return new ModelAndView(null,"login.hbs");  //Indica con que se va a renderizar el template (.hbs)
    }

    public ModelAndView crear(Request req, Response res){
        RepositorioUsuarios.admin.setPassword("12345"); //para test. la pass se hashea en el set
        Usuario usuario = null;

        try{
            usuario = RepositorioUsuarios.getInstance().buscarUsuario(req.queryParams("var_username"));
            usuario.validarContraseña(req.queryParams("var_password"));
        }catch(UsuarioInexistente e){
            return null;
        }catch (ContraseñaInvalidaException e){
            return null;
        }

        res.cookie("cookie_nombre", usuario.getNombre());

        res.redirect("/usuarios/:nombre");
        return new ModelAndView(usuario, "perfil.hbs");
    }

}
