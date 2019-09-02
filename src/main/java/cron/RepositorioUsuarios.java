package cron;

import domain.usuario.Usuario;
import exceptions.RepositorioDeUsuariosEstaInstanciado;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios {
    static boolean estaInstanciado = false;
    private List<Usuario> listaDeUsuarios = new ArrayList<>();

    private RepositorioUsuarios(){

    }

    public static RepositorioUsuarios Init(){
        if(!estaInstanciado){
            estaInstanciado = true;
            return new RepositorioUsuarios();
        }
        throw new RepositorioDeUsuariosEstaInstanciado("No se puede crear el repositorio por que ya fue creado");
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
