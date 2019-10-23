package controllers;

import cron.RepositorioGuardarropas;
import cron.RepositorioUsuarios;
import domain.evento.FrecuenciaEvento;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import domain.usuario.*;
import exceptions.ContraseñaInvalidaException;
import exceptions.UsuarioInexistente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.Arrays;


public class ControllerSesion {

    private String mensajeSesion = "Nunca compartas tu contraseña con nadie.";


    public ModelAndView mostrarLogin(Request req, Response res) {
        return new ModelAndView(new ControllerSesion(), "login.hbs");  //Indica con que se va a renderizar el template (.hbs)
    }


    public ModelAndView iniciarSesion(Request req, Response res) {
        iniciarUsuarioDePrueba();
        Usuario usuario;

        try {
            usuario = RepositorioUsuarios.getInstance().buscarUsuario(req.queryParams("query_username"));
            usuario.validarContraseña(req.queryParams("query_password"));
        } catch (UsuarioInexistente e) {
            mensajeSesion = "Usuario inexistente o contraseña invalida.";
            return new ModelAndView(this, "login.hbs");
        } catch (ContraseñaInvalidaException e) {
            mensajeSesion = "Usuario inexistente o contraseña invalida.";
            return new ModelAndView(this, "login.hbs");
        }

        res.cookie("cookie_nombre", usuario.getNombre());

        res.redirect("/perfil");
        return new ModelAndView(null, "perfil.hbs");
    }


    public ModelAndView cerrarSesion(Request req, Response res) {
        res.removeCookie("cookie_nombre");
        res.removeCookie("cookie_nombreGuardarropas");
        res.redirect("/login");
        return new ModelAndView(new ControllerSesion(), "login.hbs");
    }


    public String getMensajeSesion() {
        return mensajeSesion;
    }

    public void iniciarUsuarioDePrueba(){
        RepositorioUsuarios.admin.setPassword("12345");
        RepositorioUsuarios.admin.crearEvento("Cumpleaños de juan", RepositorioUsuarios.fechaCumpleWilly, FrecuenciaEvento.NO_SE_REPITE,"Casa de Juan");
        RepositorioUsuarios.admin.crearEvento("Cumpleaños de pepe", RepositorioUsuarios.fechaCumplePepe, FrecuenciaEvento.NO_SE_REPITE,"Casa de pepe");
        RepositorioUsuarios.admin.crearEvento("Cumpleaños de robertito", RepositorioUsuarios.fechaCumpleRoberto, FrecuenciaEvento.NO_SE_REPITE,"Casa de roberto");
    }
}




