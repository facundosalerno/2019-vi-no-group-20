package controllers;

import domain.guardarropas.*;
import domain.RepositorioGuardarropas;
import domain.usuario.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import spark.Access;
import java.util.List;


public class ControllerSesion {


    // .../guardarropas/:id/prendas

    // .../guardarropas/prendas

    public ModelAndView mostrarLogin(Request req, Response res) {

        return new ModelAndView(null,"login.hbs");  //Indica con que se va a renderizar el template (.hbs)
    }

    public ModelAndView crear(Request req, Response res){
        //Usuario usuario = RepositorioUsuario.buscarPorNombre(req.params("user"));

        //Si no existe => lanzar excepcion

        //Usuario usuario= new Usuario("foo","foo");

        //usuario.validarContrasenia(req.queryParams("pass"));

        //NUNCA GUARDAR UN ID DE USUARIO EN LA COOKIE EN TEXTO PLANO

        //res.cookie("nombre",usuario.getNombre());
        //res.cookie("uid",usuario.getId().toString());

        //redirigir al perfil
        //res.redirect("/perfil");


        //return new ModelAndView(usuario, "perfil.hbs");
        return null; //Quitar una vez que este lo que corresponde
    }

}
