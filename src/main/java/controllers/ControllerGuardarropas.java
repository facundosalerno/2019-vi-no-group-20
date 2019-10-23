package controllers;

import cron.RepositorioGuardarropas;
import cron.RepositorioUsuarios;
import domain.guardarropas.GuardarropasPremium;
import domain.usuario.Usuario;
import exceptions.UsuarioInexistente;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;

public class ControllerGuardarropas implements TransactionalOps, EntityManagerOps {

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

    @Override
    public EntityManager entityManager() {
        return null;
    }
}