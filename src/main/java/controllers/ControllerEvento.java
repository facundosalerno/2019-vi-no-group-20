package controllers;

import cron.RepositorioUsuarios;
import domain.evento.FrecuenciaEvento;
import domain.usuario.Usuario;
import exceptions.UsuarioInexistente;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;
import java.util.Collections;

public class ControllerEvento implements WithGlobalEntityManager, TransactionalOps {
    LocalDateTime hoy;

    public ModelAndView creacionEvento(Request req, Response res){
        hoy = LocalDateTime.now();
        return new ModelAndView(this, "crearEvento.hbs");
    }

    public ModelAndView crear(Request req, Response res){
        String nombre= req.cookie("cookie_nombre");
        Usuario usuario;
        try{
            usuario = RepositorioUsuarios.getInstance().buscarUsuario(nombre);
        }catch (UsuarioInexistente e){
            return new ModelAndView(null, "forbidden.hbs");
        }

        usuario.crearEvento(req.queryParams("query_nombre"), LocalDateTime.parse(req.queryParams("query_localDateTime")), FrecuenciaEvento.NO_SE_REPITE, req.queryParams("query_lugar"));
        res.redirect("/calendario");
        return new ModelAndView(null, "calendario.hbs");
    }

    public LocalDateTime getHoy() {
        return hoy;
    }
}
