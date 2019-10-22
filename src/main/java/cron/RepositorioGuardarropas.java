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


    public static Prenda armarUnaPrenda(String nombre, TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama){
        BorradorPrenda borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirNombre(nombre);
        borradorPrenda.definirTipo(tipoDePrenda);
        borradorPrenda.definirMaterial(material);
        borradorPrenda.definirColorPrimario(colorPrimario);
        borradorPrenda.definirColorSecundario(colorSecundario);
        borradorPrenda.definirTrama(trama);
        //borradorPrenda.definirImagen("/public/remera.png");
        return borradorPrenda.crearPrenda();
    }


    public static GuardarropasPremium guardarropasDelAdmin = new GuardarropasPremium(
            "guardarropas principal",
            Arrays.asList(armarUnaPrenda("remera", TipoDePrenda.REMERA, Material.ALGODON, new Color(0, 0, 255), new Color(255, 0, 0), Trama.CUADROS), armarUnaPrenda("buso", TipoDePrenda.BUSO, Material.ALGODON, new Color(0, 255, 0), new Color(0, 0, 255), Trama.LISA)),
            Arrays.asList(armarUnaPrenda("pantalon", TipoDePrenda.PANTALON, Material.JEAN, new Color(0, 255, 0), new Color(255, 0, 0), Trama.RAYADA)),
            Arrays.asList(armarUnaPrenda("zapatos", TipoDePrenda.ZAPATO, Material.CUERO, new Color(255, 0, 0), new Color(0, 0, 255), Trama.GASTADO)),
            Arrays.asList(armarUnaPrenda("anteojos", TipoDePrenda.ANTEOJOS, Material.PLASTICO, new Color(0, 255, 0), new Color(255, 0, 0), Trama.LISA))
    );

    public static GuardarropasPremium guardarropasDelAdminAuxiliar = new GuardarropasPremium(
            "guardarropas de emergencia",
            Arrays.asList(armarUnaPrenda("remera", TipoDePrenda.REMERA, Material.ALGODON, new Color(0, 0, 255), new Color(255, 0, 0), Trama.CUADROS), armarUnaPrenda("buso", TipoDePrenda.BUSO, Material.ALGODON, new Color(0, 255, 0), new Color(0, 0, 255), Trama.LISA)),
            Arrays.asList(armarUnaPrenda("pantalon", TipoDePrenda.PANTALON, Material.JEAN, new Color(0, 255, 0), new Color(255, 0, 0), Trama.RAYADA)),
            Arrays.asList(armarUnaPrenda("zapatos", TipoDePrenda.ZAPATO, Material.CUERO, new Color(255, 0, 0), new Color(0, 0, 255), Trama.GASTADO)),
            Arrays.asList(armarUnaPrenda("anteojos", TipoDePrenda.ANTEOJOS, Material.PLASTICO, new Color(0, 255, 0), new Color(255, 0, 0), Trama.LISA))
    );
}