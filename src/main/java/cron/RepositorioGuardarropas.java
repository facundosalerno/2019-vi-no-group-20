package cron;

import domain.guardarropas.Guardarropas;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import domain.usuario.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioGuardarropas {

    private static RepositorioGuardarropas instance = null;

    private List<Guardarropas> listaDeGuardarropas = new ArrayList<>();

    private static final Color rojo = new Color(255, 0, 0);
    private static final Color verde = new Color(0, 255, 0);
    private static final Color azul = new Color(0, 0, 255);

    private RepositorioGuardarropas(){}

    public static RepositorioGuardarropas getInstance() {
        if(instance == null)
            return instance = new RepositorioGuardarropas();
        return instance;
    }

    public static Prenda armarUnaPrenda(String nombre,TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama){
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

    /* No tiene sentido */
    public GuardarropasPremium findByUsuario(Usuario usuario) {
        GuardarropasPremium guardarropas = new GuardarropasPremium("ropa sport");
        guardarropas.agregarPrendaSuperior(armarUnaPrenda("Remera copada",TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS));
        guardarropas.agregarPrendaInferior(armarUnaPrenda("Pantalon de laburo",TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA));
        return guardarropas;
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



    public static GuardarropasPremium guardarropasDelAdmin = new GuardarropasPremium(
            Arrays.asList(armarUnaPrenda("remera", TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS), armarUnaPrenda("buso", TipoDePrenda.BUSO, Material.ALGODON, azul, verde, Trama.LISA)),
            Arrays.asList(armarUnaPrenda("pantalon", TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA)),
            Arrays.asList(armarUnaPrenda("zapatos", TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO)),
            Arrays.asList(armarUnaPrenda("anteojos", TipoDePrenda.ANTEOJOS, Material.PLASTICO, verde, rojo, Trama.LISA))
    );

    public static GuardarropasPremium guardarropasDelAdminAuxiliar = new GuardarropasPremium(
            Arrays.asList(armarUnaPrenda("remera", TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS), armarUnaPrenda("buso", TipoDePrenda.BUSO, Material.ALGODON, azul, verde, Trama.LISA)),
            Arrays.asList(armarUnaPrenda("pantalon", TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA)),
            Arrays.asList(armarUnaPrenda("zapatos", TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO)),
            Arrays.asList(armarUnaPrenda("anteojos", TipoDePrenda.ANTEOJOS, Material.PLASTICO, verde, rojo, Trama.LISA))
    );


}