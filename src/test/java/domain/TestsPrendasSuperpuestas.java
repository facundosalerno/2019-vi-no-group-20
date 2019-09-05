package domain;

import clima.OpenWeather;
import clima.TemperaturaOpenWeather;
import domain.atuendo.Atuendo;
import domain.capaPrenda.CapaCompuesta;
import domain.capaPrenda.CapaSimple;
import domain.guardarropas.Guardarropas;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class TestsPrendasSuperpuestas {
    private Atuendo atuendo;

    private Prenda zapatosFormales;   //TODO: Para tests guardarropas
    private Prenda zapatosSalida;

    private Prenda pantalonParaSalida;
    private Prenda pantalonFormal;   //TODO: Para tests guardarropas

    private Prenda anteojos;

    private Prenda camperaParaSalida;
    private Prenda camperaMichelin;
    private Prenda busoInformal;
    private Prenda busoFormal;
    private Prenda sweaterFormal;
    private Prenda remeraCanchera;
    private Prenda remeraDeDia;
    private Prenda camisaFormalBlanca;
    private Prenda camisaFormalAzul;
    private Prenda camisaSalida;

    private CapaSimple capaZapatosFormales;
    private CapaSimple capaPantalonParaSalida;
    private CapaSimple capaAnteojos;
    private CapaSimple capaCamperaParaSalida;
    private CapaSimple capaCamperaMichelin;
    private CapaSimple capaBusoInformal;
    private CapaSimple capaSweaterFormal;
    private CapaSimple capaRemeraDeDia;
    private CapaSimple capaCamisaFormalBlanca;
    private CapaSimple capaCamisaSalida;



    private Guardarropas guardarropasInvierno;

    TemperaturaOpenWeather nuevoClima;
    OpenWeather nuevoMeteorologo;

    @Before
    public void init() {
        //Instanciaciones previas a los TEST
        Color rojo = new Color(255, 0, 0);
        Color verde = new Color(0, 255, 0);
        Color azul = new Color(0, 0, 255);
        Color blanco = new Color(255, 255, 255);
        Color negro = new Color(0, 0, 0);

        //zapatosSalida = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.GAMUZA, azul, null, Trama.GASTADO);  //TODO: Para tests guardarropas
        zapatosFormales = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.CUERO, negro, null, Trama.LISA);

        pantalonParaSalida = armarUnaPrenda(TipoDePrenda.PANTALON, Material.JEAN, blanco, null, Trama.GASTADO);
        //pantalonFormal = armarUnaPrenda(TipoDePrenda.PANTALON, Material.POLIESTER, negro, null, Trama.LISA);  //TODO: Para tests guardarropas

        anteojos = armarUnaPrenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, verde, rojo, Trama.LISA);


        //busoFormal = armarUnaPrenda(TipoDePrenda.BUSO, Material.ALGODON, azul, null, Trama.LISA);   //TODO: Para tests guardarropas
        busoInformal = armarUnaPrenda(TipoDePrenda.BUSO, Material.ALGODON, azul, verde, Trama.RAYADA);
        sweaterFormal = armarUnaPrenda(TipoDePrenda.SWEATER,Material.LINO, azul, null, Trama.LISA);

        camperaParaSalida = armarUnaPrenda(TipoDePrenda.CAMPERA, Material.JEAN, verde, null, Trama.GASTADO);
        camperaMichelin = armarUnaPrenda(TipoDePrenda.CAMPERA, Material.PLUMA, azul, null, Trama.LISA);

        camisaSalida = armarUnaPrenda(TipoDePrenda.CAMISA, Material.JEAN, azul, blanco, Trama.ESCOCESA);
        //camisaFormalAzul = armarUnaPrenda(TipoDePrenda.CAMISA, Material.SEDA, azul, null, Trama.LISA);  //TODO: Para tests guardarropas
        camisaFormalBlanca = armarUnaPrenda(TipoDePrenda.CAMISA, Material.LINO, blanco, null, Trama.LISA);

        //remeraCanchera = armarUnaPrenda(TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.RAYADA);  //TODO: Para tests guardarropas
        remeraDeDia = armarUnaPrenda(TipoDePrenda.REMERA, Material.ALGODON, azul, null, Trama.LISA);

        guardarropasInvierno = new GuardarropasPremium(Arrays.asList(sweaterFormal,remeraDeDia, camisaSalida, busoInformal, camperaParaSalida, camisaFormalBlanca, camperaMichelin), Arrays.asList(pantalonParaSalida), Arrays.asList(zapatosFormales), Arrays.asList(anteojos));

        guardarropasInvierno = new GuardarropasPremium(Arrays.asList(sweaterFormal,remeraDeDia, camisaSalida, busoInformal, camperaParaSalida, camisaFormalBlanca, camperaMichelin), Arrays.asList(pantalonParaSalida), Arrays.asList(zapatosFormales), Arrays.asList(anteojos));


        nuevoClima =	mock(TemperaturaOpenWeather.class);
        when(nuevoClima.getTemperature()).thenReturn(8.0);

        nuevoMeteorologo = mock (OpenWeather.class);
        when (nuevoMeteorologo.obtenerClima()).thenReturn(nuevoClima);

        //TODO: cada atuendo tiene capas, entonces tengo que generar las capas de los atuendos para compararlas con el resultado de sugerirAtuendo. VER TEMA Clima en sugerir atuendo


        capaCamperaParaSalida= new CapaSimple(camperaParaSalida);
        capaCamperaMichelin= new CapaSimple(camperaMichelin);
        capaBusoInformal= new CapaSimple(busoInformal);
        capaSweaterFormal= new CapaSimple(sweaterFormal);
        capaRemeraDeDia= new CapaSimple(remeraDeDia);
        capaCamisaFormalBlanca= new CapaSimple(camisaFormalBlanca);
        capaCamisaSalida= new CapaSimple(camisaSalida);

        capaPantalonParaSalida= new CapaSimple(pantalonParaSalida);;
        capaAnteojos= new CapaSimple(anteojos);
        capaZapatosFormales = new CapaSimple(zapatosFormales);


       // CapaCompuesta parteSuperiorInvierno = new CapaCompuesta(Arrays.asList(remera, buso, campera));




       // Assert.assertEquals(guardarropasInvierno.sugerirAtuendo(nuevoClima), Arrays.asList());

    }

    public Prenda armarUnaPrenda(TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama) {
        BorradorPrenda borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirTipo(tipoDePrenda);
        borradorPrenda.definirMaterial(material);
        borradorPrenda.definirColorPrimario(colorPrimario);
        if (colorSecundario != null) {

            borradorPrenda.definirColorSecundario(colorSecundario);
        }
        borradorPrenda.definirTrama(trama);
        return borradorPrenda.crearPrenda();
    }

    /*@Test(expected = AtuendoInvalidoException.class)
    public void verificarQueNoSeCreaAtuendoConPrendasSuperpuestasDeDistintaCategoria(){
        new Atuendo(Arrays.asList(camisa, pantalon), pantalon, zapatos, anteojos);
    }*/

    //TODO: Test para verificar que siempre se crean capas con prendas de igual categoria

    //TODO:Test para verificar que se dispara una excepcion si no hay capas creadas debido a que no se satisface la temperatura

    @Test
    public void generarAtuendoTiraExcepcionSiNohayCapasQueSatisfaganLaTemperatura() {

    }

    //TODO: Test para verificar que las capas generadas esten ordenadas

    //TODO: Test para verificar que todas las capas son de distinto nivel

    @Test




    public void seGeneranAtuendosEsperados(){      //TODO: REVISAR CASOS DE FALLO QUE ESTAN COMENTADOS
        
        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).size() == 12);

        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaRemeraDeDia,capaBusoInformal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
//        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaRemeraDeDia,capaSweaterFormal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaRemeraDeDia,capaBusoInformal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
//        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaRemeraDeDia,capaSweaterFormal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaSalida,capaBusoInformal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
//        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaSalida,capaSweaterFormal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
//        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaFormalBlanca,capaSweaterFormal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
//        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaFormalBlanca,capaBusoInformal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
//        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaFormalBlanca,capaSweaterFormal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
//        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaFormalBlanca,capaBusoInformal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
//        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaSalida,capaSweaterFormal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
        Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().anyMatch(sugerencia->sugerencia.equals(new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaSalida,capaBusoInformal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));

        /*Assert.assertTrue(guardarropasInvierno.sugerirAtuendo(nuevoMeteorologo).stream().equals(Arrays.asList(new Atuendo(new CapaCompuesta(Arrays.asList(capaRemeraDeDia,capaBusoInformal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos),
        new Atuendo(new CapaCompuesta(Arrays.asList(capaRemeraDeDia,capaSweaterFormal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos),
        new Atuendo(new CapaCompuesta(Arrays.asList(capaRemeraDeDia,capaBusoInformal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos),
        new Atuendo(new CapaCompuesta(Arrays.asList(capaRemeraDeDia,capaSweaterFormal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos),

        new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaSalida,capaBusoInformal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos),
        new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaSalida,capaSweaterFormal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos),
        new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaFormalBlanca,capaSweaterFormal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos),
        new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaFormalBlanca,capaBusoInformal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos),
        new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaFormalBlanca,capaSweaterFormal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos),
        new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaFormalBlanca,capaBusoInformal,capaCamperaParaSalida)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos),
        new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaSalida,capaSweaterFormal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos),
        new Atuendo(new CapaCompuesta(Arrays.asList(capaCamisaSalida,capaBusoInformal,capaCamperaMichelin)),capaPantalonParaSalida,capaZapatosFormales,capaAnteojos))));
        */
    }
}
