package controllers;

import cron.RepositorioUsuarios;
import domain.evento.Evento;
import domain.prenda.Prenda;
import domain.usuario.Usuario;
import exceptions.UsuarioInexistente;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import scala.Array;
import scala.math.Ordering;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerCalendario implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {

    public ModelAndView mostrar(Request req, Response res){
        String nombre= req.cookie("cookie_nombre");
        Usuario usuario;
        try{
            usuario = RepositorioUsuarios.getInstance().buscarUsuario(nombre);
        }catch (UsuarioInexistente e){
            return new ModelAndView(null, "forbidden.hbs");
        }
        Collections.sort(usuario.getEventos());
        ControllerMonth controllerMonth = new ControllerMonth(usuario.getEventos());
        return new ModelAndView(controllerMonth, "calendario.hbs");
    }

}
