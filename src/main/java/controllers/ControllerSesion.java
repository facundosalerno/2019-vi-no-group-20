package controllers;

import domain.guardarropas.*;
import domain.RepositorioGuardarropas;
import domain.usuario.*;
import exceptions.ContraseñaInvalidaException;
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
        /* Usuario de ejemplo. Actualizar los constructores en el domain */
        Usuario usuario= new Usuario("Admin","1234");

        try{
            //usuario = RepositorioUsuario.buscarPorNombre(req.params("nombre o email o algo parabuscarlo"));
            usuario.validarContraseña(req.queryParams("var_password"));
        }/*catch(UsuarioInexistente e){
            return null;
        }*/catch (ContraseñaInvalidaException e){
            return null;
        }

        //NUNCA GUARDAR UN ID DE USUARIO EN LA COOKIE EN TEXTO PLANO

        res.cookie("cookie_nombre",usuario.getNombre());
        //res.cookie("cookie_id",usuario.getId().toString());

        res.redirect("/perfil");
        return new ModelAndView(usuario, "perfil.hbs");
    }

}
