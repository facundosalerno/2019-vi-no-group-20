package domain;

import domain.guardarropas.Guardarropas;
import domain.guardarropas.GuardarropasLimitado;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import domain.usuario.Usuario;

public class RepositorioGuardarropas {

    private static final RepositorioGuardarropas INSTANCE = new RepositorioGuardarropas();

    Color rojo = new Color(255, 0, 0);
    Color verde = new Color(0, 255, 0);
    Color azul = new Color(0, 0, 255);

    public static RepositorioGuardarropas instance() {
        return INSTANCE;
    }

    public Prenda armarUnaPrenda(String nombre,TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama){
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

    public GuardarropasPremium findByUsuario(Usuario usuario) {
        GuardarropasPremium guardarropas = new GuardarropasPremium("ropa sport");
        guardarropas.agregarPrendaSuperior(armarUnaPrenda("Remera copada",TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS));
        guardarropas.agregarPrendaInferior(armarUnaPrenda("Pantalon de laburo",TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA));
        return guardarropas;
    }

}