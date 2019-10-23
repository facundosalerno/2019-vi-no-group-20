package controllers;

import cron.RepositorioUsuarios;
import domain.usuario.Usuario;
import exceptions.UsuarioInexistente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;
import java.util.Collections;

public class ControllerEvento {
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

        return new ModelAndView(null, "calendario.hbs");
    }

    public LocalDateTime getHoy() {
        return hoy;
    }
}
