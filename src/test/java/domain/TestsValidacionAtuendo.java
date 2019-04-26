package domain;

import exceptions.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TestsValidacionAtuendo {
    private Atuendo atuendo;
    private Prenda zapatos;
    private Prenda remera;
    private Prenda camisa;
    private Prenda pantalon;
    private Prenda blusa;
    private Prenda shorts;
    private Prenda zapatillas;
    private Prenda pollera;
    private Prenda ojotas;
    private Prenda alpargatas;


    @Before
    public void init(){
        //Instanciaciones previas a los TEST
        BorradorPrenda borradorPrenda;
        Color color1 = new Color(200, 100, 100);
        Color color2 = new Color(90, 80, 10);

        borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(TipoDePrenda.ZAPATO);
        borradorPrenda.definirMaterial(Material.CUERO);
        borradorPrenda.definirColorPrimario(color1);
        borradorPrenda.definirColorSecundario(color2);
        zapatos = borradorPrenda.crearPrenda();

        borradorPrenda = new BorradorPrenda(); //Para no arrastrar cosas como el color secundario
        borradorPrenda.definirTipo(TipoDePrenda.REMERA);
        borradorPrenda.definirMaterial(Material.ALGODON);
        borradorPrenda.definirColorPrimario(color2);
        borradorPrenda.definirTrama(Trama.CUADROS);
        remera = borradorPrenda.crearPrenda();

        borradorPrenda = new BorradorPrenda(); //Para no arrastrar cosas como el color secundario
        borradorPrenda.definirTipo(TipoDePrenda.CAMISA);
        borradorPrenda.definirMaterial(Material.POLIESTER);
        borradorPrenda.definirColorPrimario(color2);
        borradorPrenda.definirTrama(Trama.RAYADA);
        camisa = borradorPrenda.crearPrenda();

        borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(TipoDePrenda.PANTALON);
        borradorPrenda.definirMaterial(Material.JEAN);
        borradorPrenda.definirColorPrimario(color1);
        borradorPrenda.definirTrama(Trama.GASTADO);
        pantalon = borradorPrenda.crearPrenda();

        borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(TipoDePrenda.SHORT);
        borradorPrenda.definirMaterial(Material.JEAN);
        borradorPrenda.definirColorPrimario(color1);
        borradorPrenda.definirTrama(Trama.GASTADO);
        shorts = borradorPrenda.crearPrenda();

        borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(TipoDePrenda.BLUSA);
        borradorPrenda.definirMaterial(Material.ALGODON);
        borradorPrenda.definirColorPrimario(color1);
        blusa = borradorPrenda.crearPrenda();

        borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(TipoDePrenda.ZAPATILLA);
        borradorPrenda.definirMaterial(Material.GAMUZA);
        borradorPrenda.definirColorPrimario(color1);
        zapatillas = borradorPrenda.crearPrenda();

        borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(TipoDePrenda.POLLERA);
        borradorPrenda.definirMaterial(Material.POLIESTER);
        borradorPrenda.definirColorPrimario(color1);
        borradorPrenda.definirTrama(Trama.CUADROS);
        pollera = borradorPrenda.crearPrenda();

        borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(TipoDePrenda.OJOTAS);
        borradorPrenda.definirMaterial(Material.GOMA);
        borradorPrenda.definirColorPrimario(color1);
        ojotas = borradorPrenda.crearPrenda();

        borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(TipoDePrenda.ALPARGATAS);
        borradorPrenda.definirMaterial(Material.ALGODON);
        borradorPrenda.definirColorPrimario(color1);
        borradorPrenda.definirTrama(Trama.CUADROS);
        alpargatas = borradorPrenda.crearPrenda();


        Guardarropas guardarropas_1= new Guardarropas(Arrays.asList(remera,camisa),Arrays.asList(shorts, pantalon),Arrays.asList(zapatos,zapatillas));
        Guardarropas guardarropas_2= new Guardarropas(Arrays.asList(blusa, camisa),Arrays.asList(pollera),Arrays.asList(ojotas,alpargatas));
        Usuario carlos= new Usuario(Arrays.asList(guardarropas_1,guardarropas_2));
    }

    
   
    //Test de sugerencias de varios guardarropas (que las listas generadas por los distintos guardarropas sean distintas)
    /*
     @Test
     public void crear_sugerencias_independientes(){
        atuendos1 = guardarropas1.sugerir();
        atuendos2 = guardarropas2.sugerir();
        
         Assert.assertFalse(Arrays.equals(atuendos1, atuendos2));
        
    }
    */
    //--CREANDO EL ATUENDO--

    //Test de atuendo valido
    @Test
    public void crear_atuendo_valido(){
        atuendo = new Atuendo(remera, pantalon, zapatos);
        Assert.assertNotEquals(null, atuendo);
        //TODO: MEJORAR ESTO
    }




    //--CHEQUEANDO EXCEPCIONES--

    //Test para capturar atuendo invalido exception
    @Test (expected = AtuendoInvalidoException.class)
    public void crear_atuendo_invalido(){
        atuendo = new Atuendo (pantalon, pantalon, zapatos);
    }
}
