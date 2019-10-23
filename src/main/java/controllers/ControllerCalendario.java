package controllers;

import cron.RepositorioUsuarios;
import domain.evento.Evento;
import domain.prenda.Prenda;
import domain.usuario.Usuario;
import exceptions.UsuarioInexistente;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import scala.math.Ordering;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerCalendario implements TransactionalOps, EntityManagerOps {

    public ModelAndView mostrar(Request req, Response res){
        String nombre= req.cookie("cookie_nombre");
        Usuario usuario;
        try{
            usuario = RepositorioUsuarios.getInstance().buscarUsuario(nombre);
        }catch (UsuarioInexistente e){
            return new ModelAndView(null, "forbidden.hbs");
        }
        Collections.sort(usuario.getEventos());
        return new ModelAndView(usuario, "calendario.hbs");
    }

    @Override
    public EntityManager entityManager() {
        return null;
    }
}
