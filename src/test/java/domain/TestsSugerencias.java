package domain;

import clima.AccuWeather;
import clima.TemperaturaAccuWeather;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestsSugerencias {
    private Guardarropas guardarropasDeWilly;

    private Prenda musculosa;
    private Prenda remeraMangaLarga;

    private Prenda pantalon;
    private Prenda trajeDeBaño;

    private Prenda zapatos;
    private Prenda ojotas;

    private Prenda anteojos;

    @Before
    public void init(){
        Color rojo = new Color(255, 0, 0);
        Color verde = new Color(0, 255, 0);
        Color azul = new Color(0, 0, 255);

        musculosa = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO);
        remeraMangaLarga = armarUnaPrenda(TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS);

        pantalon = armarUnaPrenda(TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA);
        trajeDeBaño = armarUnaPrenda(TipoDePrenda.SHORT, Material.ALGODON, verde, rojo, Trama.LISA);

        zapatos = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO);
        ojotas = armarUnaPrenda(TipoDePrenda.OJOTAS, Material.PLASTICO, azul, rojo, Trama.CUADROS);

        anteojos= armarUnaPrenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, verde, rojo, Trama.LISA);

        guardarropasDeWilly = new GuardarropasPremium(Arrays.asList(musculosa, remeraMangaLarga), Arrays.asList(pantalon, trajeDeBaño), Arrays.asList(zapatos, ojotas), Arrays.asList(anteojos));

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
/*
    @Test
    public void verificarSugerenciasAcordeAlVerano(){
        AccuWeather meteorologoAccuweather = mock(AccuWeather.class);
        TemperaturaAccuWeather temperaturaImpostora= mock(TemperaturaAccuWeather.class);
        temperaturaImpostora.setValue(32);
        when(meteorologoAccuweather.obtenerClima()).thenReturn(temperaturaImpostora);

        List<Atuendo> sugerencias = guardarropasDeWilly.sugerirAtuendo(meteorologoAccuweather);
        Atuendo posibleAtuendoSugerido = new Atuendo(Arrays.asList(musculosa), trajeDeBaño, ojotas, anteojos);
    }

    @Test
    public void verificarSugerenciasAcordeAlInvierno(){
        AccuWeather meteorologoAccuweather = mock(AccuWeather.class);
        TemperaturaAccuWeather temperaturaImpostora= mock(TemperaturaAccuWeather.class);
        temperaturaImpostora.setValue(10);
        when(meteorologoAccuweather.obtenerClima()).thenReturn(temperaturaImpostora);

        guardarropasDeWilly.sugerirAtuendo(meteorologoAccuweather);
    }*/
}
