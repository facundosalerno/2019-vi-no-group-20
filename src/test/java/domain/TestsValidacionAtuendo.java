package domain;

import exceptions.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestsValidacionAtuendo {
    private Atuendo atuendo;
    private Prenda zapatos;
    private Prenda remera;
    private Prenda pantalon;

    @Before
    public void init() {
        //Instanciaciones previas a los TEST
        Color rojo = new Color(255, 0, 0);
        Color verde = new Color(0, 255, 0);
        Color azul = new Color(0, 0, 255);

        zapatos = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO);
        remera = armarUnaPrenda(TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS);
        pantalon = armarUnaPrenda(TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA);


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
            new Atuendo(remera, pantalon, zapatos);
        }catch (AtuendoInvalidoException ex){
            Assert.fail();
        }
    }

    //Test para verificar que no deberia crearse un atuendo invalido
    @Test (expected = AtuendoInvalidoException.class)
    public void crearAtuendoInvalido() {
        new Atuendo(pantalon, pantalon, zapatos);
    }
}
