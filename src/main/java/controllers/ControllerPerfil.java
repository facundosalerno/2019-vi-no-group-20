package controllers;

import cron.RepositorioUsuarios;
import domain.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerPerfil {

    public ModelAndView mostrar(Request req, Response res){


        //Usuario usuario = new Usuario("foo","foo");
        //Map<String,String> model = HashMap<>;
        //SE USA SOLO EL ID, SE RECUPERA DE LA COOKIE Y CON EL MISMO SE BUSCA EN LA BASE DE DATOS PARA OBTENER TODOS LOS OTROS DATOS

        String nombre= req.cookie("cookie_nombre"); //MAL
        Usuario usuario = RepositorioUsuarios.getInstance().buscarUsuario(nombre);

        return new ModelAndView(usuario, "perfil.hbs");
    }
}
