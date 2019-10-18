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

    private String mensajeSesion = "Nunca compartas tu contraseña con nadie.";

    public ModelAndView mostrarLogin(Request req, Response res) {
        return new ModelAndView(new ControllerSesion(),"login.hbs");  //Indica con que se va a renderizar el template (.hbs)
    }

    public ModelAndView crear(Request req, Response res){
        RepositorioUsuarios.admin.setPassword("12345"); //para test. la pass se hashea en el set
        Usuario usuario;

        try{
            usuario = RepositorioUsuarios.getInstance().buscarUsuario(req.queryParams("var_username"));
            usuario.validarContraseña(req.queryParams("var_password"));
        }catch(UsuarioInexistente e){
            mensajeSesion = "Usuario inexistente.";
            return new ModelAndView(this, "login.hbs");
        }catch (ContraseñaInvalidaException e){
            mensajeSesion = "Contraseña invalida.";
            return new ModelAndView(this, "login.hbs");
        }

        res.cookie("cookie_nombre", usuario.getNombre());

        res.redirect("/usuarios/:nombre");
        return new ModelAndView(usuario, "perfil.hbs");
    }

    public String getMensajeSesion(){
        return mensajeSesion;
    }
}
