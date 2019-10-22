package cron;

import domain.guardarropas.Guardarropas;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import domain.usuario.Usuario;
import exceptions.NoExisteGuardarropasException;
import exceptions.UsuarioInexistente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioGuardarropas {

    private static RepositorioGuardarropas instance = null;

    private List<Guardarropas> listaDeGuardarropas = new ArrayList<>();

    private RepositorioGuardarropas(){}

    public static RepositorioGuardarropas getInstance() {
        if(instance == null)
            return instance = new RepositorioGuardarropas();
        return instance;
    }


    public List<Guardarropas> getListaDeGuardarropas() {
        return listaDeGuardarropas;
    }

    public void agregarGuardarropas(Guardarropas guardarropas){
        if(!listaDeGuardarropas.contains(guardarropas))
            listaDeGuardarropas.add(guardarropas);
    }

    public void eliminarGuardarropas(Guardarropas guardarropas){
        if(listaDeGuardarropas.contains(guardarropas))
            listaDeGuardarropas.remove(guardarropas);
    }

    public Guardarropas buscarGuardarropas(String nombre){
        Guardarropas guardarropas = listaDeGuardarropas.stream().filter(g -> g.getNombre().equals(nombre)).findFirst().orElse(null);
        if(guardarropas == null)
            throw new NoExisteGuardarropasException();
        return guardarropas;
    }
}