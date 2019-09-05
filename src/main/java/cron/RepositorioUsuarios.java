package cron;

import domain.usuario.Usuario;
import exceptions.RepositorioDeUsuariosEstaInstanciado;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios {
    static private RepositorioUsuarios instancia = null;
    private List<Usuario> listaDeUsuarios = new ArrayList<>();

    private RepositorioUsuarios(){

    }

    public static RepositorioUsuarios getInstance(){
        if(instancia == null){
            return instancia = new RepositorioUsuarios();
        }
        return instancia;
    }


    public void agregarUsuario(Usuario usuario){
        if(!listaDeUsuarios.contains(usuario))
            listaDeUsuarios.add(usuario);
    }

    public void eliminarUsuario(Usuario usuario){
        if(listaDeUsuarios.contains(usuario))
            listaDeUsuarios.remove(usuario);
    }

    public List<Usuario> getListaDeUsuarios(){
        return listaDeUsuarios;
    }
}
