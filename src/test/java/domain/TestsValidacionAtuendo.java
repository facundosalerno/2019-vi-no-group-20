package domain;

import domain.atuendo.Atuendo;
import domain.capaPrenda.Capa;
import domain.capaPrenda.CapaSimple;
import domain.prenda.*;
import exceptions.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestsValidacionAtuendo {
    private Prenda prendaZapatos;
    private Prenda prendaRemera;
    private Prenda prendaPantalon;
    private Prenda prendaAnteojos;
    private Capa zapatos;
    private Capa remera;
    private Capa pantalon;
    private Capa anteojos;

    @Before
    public void init() {
        //Instanciaciones previas a los TEST
        Color rojo = new Color(255, 0, 0);
        Color verde = new Color(0, 255, 0);
        Color azul = new Color(0, 0, 255);

        prendaZapatos = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO);
        prendaRemera = armarUnaPrenda(TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS);
        prendaPantalon = armarUnaPrenda(TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA);
        prendaAnteojos = armarUnaPrenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, verde, rojo, Trama.LISA);

        zapatos = new CapaSimple(prendaZapatos);
        remera = new CapaSimple(prendaRemera);
        pantalon = new CapaSimple(prendaPantalon);
        anteojos = new CapaSimple(prendaAnteojos);

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


    //Test para verificar que podemos crear un atuendo correctamente
    @Test
    public void crearAtuendoValido() {
        try{
            new Atuendo(remera, pantalon, zapatos, anteojos);
        }catch (AtuendoInvalidoException exc){
            Assert.fail();
        }
    }

    //Test para verificar que no deberia crearse un atuendo invalido
    @Test (expected = AtuendoInvalidoException.class)
    public void crearAtuendoInvalido() {
        new Atuendo(pantalon, pantalon, zapatos, anteojos);
    }
}
