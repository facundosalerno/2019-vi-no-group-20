package domain;

import exceptions.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Arrays;

public class TestsValidacionUsuarioYGuardarropas {
    private Prenda zapatos;
    private Prenda remera;
    private Prenda pantalon;
    private Prenda blusa;
    private Prenda shorts;
    private Prenda zapatillas;
    private Prenda pollera;
    private Prenda ojotas;
    private Prenda alpargatas;
    private Prenda camisa;
    private Prenda pulseras;
    private Prenda anteojos;

    @Before
    public void init() {
        //Instanciaciones previas a los TEST
        Color rojo = new Color(255, 0, 0);
        Color verde = new Color(0, 255, 0);
        Color azul = new Color(0, 0, 255);

        zapatos = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO);
        remera = armarUnaPrenda(TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS);
        pantalon = armarUnaPrenda(TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA);
        shorts = armarUnaPrenda(TipoDePrenda.SHORT, Material.JEAN, verde, rojo, Trama.GASTADO);
        blusa = armarUnaPrenda(TipoDePrenda.BLUSA, Material.ALGODON, azul, verde, Trama.LISA);
        zapatillas = armarUnaPrenda(TipoDePrenda.ZAPATILLA, Material.GAMUZA, azul, verde, Trama.LISA);
        pollera = armarUnaPrenda(TipoDePrenda.POLLERA, Material.POLIESTER, rojo, verde, Trama.CUADROS);
        ojotas = armarUnaPrenda(TipoDePrenda.OJOTAS, Material.GOMA, azul, rojo, Trama.LISA);
        alpargatas = armarUnaPrenda(TipoDePrenda.ALPARGATAS, Material.ALGODON, azul, rojo, Trama.CUADROS);
        camisa = armarUnaPrenda(TipoDePrenda.CAMISA, Material.ALGODON, azul, rojo, Trama.CUADROS);
        pulseras=  armarUnaPrenda(TipoDePrenda.PULSERA, Material.PLASTICO, azul, rojo, Trama.LISA);
        anteojos = armarUnaPrenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, rojo, verde, Trama.LISA);
    }

    
    
    public Prenda armarUnaPrenda(TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama){
        BorradorPrenda borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(tipoDePrenda);
        borradorPrenda.definirMaterial(material);
        borradorPrenda.definirColorPrimario(colorPrimario);
        borradorPrenda.definirColorSecundario(colorSecundario);
        borradorPrenda.definirTrama(trama);
        return borradorPrenda.crearPrenda();
    }

    //Verificar que se pueda crear correctamente un guardarropas
    @Test
    public void crearUnGuardarropasValido(){
        try{
            Guardarropas guardarropasDeVerano = new Guardarropas(Arrays.asList(remera, blusa), Arrays.asList(shorts, pantalon), Arrays.asList(zapatos, zapatillas),Arrays.asList(pulseras, anteojos));
        }catch (NoPerteneceALaCategoriaException ex){
            Assert.fail();
        }
    }

// Verificar que no se permita tener un guardarropa vacio

   @Test(expected= NoPermiteGuardarropaIncompletoException.class)
   public void pedirSugerenciaConGuardarropaVacio(){
         Guardarropas guardarropasDeInvierno= new Guardarropas (Arrays.asList(),Arrays.asList(),Arrays.asList(),Arrays.asList());
         guardarropasDeInvierno.sugerirAtuendo();
    }

 // Verificar que no de sugerencias si no tiene prenda superior
    @Test(expected= NoPermiteGuardarropaIncompletoException.class)
    public void pedirSugerenciaConGuardarropaIncompleto(){
        Guardarropas guardarropasDeportivo= new Guardarropas (Arrays.asList(),Arrays.asList(pantalon),Arrays.asList(zapatillas), Arrays.asList(anteojos));
        guardarropasDeportivo.sugerirAtuendo();
}

}
    //TODO: preguntar alguna manera copada de testear las sugerencias de guardarropas

