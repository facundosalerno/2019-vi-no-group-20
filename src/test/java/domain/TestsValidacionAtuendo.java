package domain;

import exceptions.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestsValidacionAtuendo {
    private Atuendo atuendo;
    private Prenda zapato;
    private Prenda remera;
    private Prenda pantalon;

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
        zapato = borradorPrenda.crearPrenda();

        borradorPrenda = new BorradorPrenda(); //Para no arrastrar cosas como el color secundario
        borradorPrenda.definirTipo(TipoDePrenda.REMERA);
        borradorPrenda.definirMaterial(Material.ALGODON);
        borradorPrenda.definirColorPrimario(color2);
        borradorPrenda.definirTrama(Trama.CUADROS);
        remera = borradorPrenda.crearPrenda();

        borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(TipoDePrenda.PANTALON);
        borradorPrenda.definirMaterial(Material.JEAN);
        borradorPrenda.definirColorPrimario(color1);
        borradorPrenda.definirTrama(Trama.GASTADO);
        pantalon = borradorPrenda.crearPrenda();
        
        guardarropas_1= new Guardarropas([remera,musculosa],[bermnuda, pantalon],[zapato,zapatilla]);
        guardarropas_2= new Guardarropas([blusa, camisa],[pollera],[ojota,sandalia]);
        carlos= new Usuario([guardarropas1,guardarropas2]);
    }

    
   
    //Test de sugerencias de varios guardarropas (que las listas generadas por los distintos guardarropas sean distintas)

   
    
    //--CREANDO EL ATUENDO--

    //Test de atuendo valido
    @Test
    public void crear_atuendo_valido(){
        atuendo = new Atuendo(remera, pantalon, zapato);
        Assert.assertNotEquals(null, atuendo);
        //TODO: MEJORAR ESTO
    }




    //--CHEQUEANDO EXCEPCIONES--

    //Test para capturar atuendo invalido exception
    @Test (expected = AtuendoInvalidoException.class)
    public void crear_atuendo_invalido(){
        atuendo = new Atuendo (pantalon, pantalon, zapato);
    }
}
