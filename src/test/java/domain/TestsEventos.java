package domain;

import clima.AccuWeather;
import clima.Meteorologo;
import clima.TemperaturaAccuWeather;
import domain.atuendo.Atuendo;
import domain.evento.Evento;
import domain.evento.FrecuenciaEvento;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import domain.usuario.TipoDeUsuario;
import domain.usuario.Usuario;
import exceptions.TodaviaNoEstaCercaElEvento;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestsEventos {
    private Usuario panchoPepeGil;
    private Prenda zapatos;
    private Prenda remera;
    private Prenda pantalon;
    private Prenda anteojos;

    @Before
    public void init(){
        Color rojo = new Color(255, 0, 0);
        Color verde = new Color(0, 255, 0);
        Color azul = new Color(0, 0, 255);

        zapatos = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO);
        remera = armarUnaPrenda(TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS);
        pantalon = armarUnaPrenda(TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA);
        anteojos= armarUnaPrenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, verde, rojo, Trama.LISA);

        panchoPepeGil = new Usuario(Arrays.asList(new GuardarropasPremium(Arrays.asList(remera, remera), Arrays.asList(pantalon, pantalon, pantalon), Arrays.asList(zapatos, zapatos, zapatos), Arrays.asList(anteojos))), TipoDeUsuario.PREMIUM);

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

    /* Testea que las sugerencias solo se realizan cuando el evento esta proximo */
    @Test(expected = TodaviaNoEstaCercaElEvento.class)
    public void verificarQueSoloSeHacenSugerenciasAlEstarProximoElEvento(){
        LocalDateTime fechaCumpleWilly = LocalDateTime.of(2020,06,20,20,30);
        panchoPepeGil.crearEvento("Cumpleaños de juan", fechaCumpleWilly, FrecuenciaEvento.ANUAL,"Casa de Juan");

        AccuWeather meteorologoAccuweather = mock(AccuWeather.class);
        TemperaturaAccuWeather temperaturaImpostora= mock(TemperaturaAccuWeather.class);
        temperaturaImpostora.setValue(20);
        when(meteorologoAccuweather.obtenerClima()).thenReturn(temperaturaImpostora);
        panchoPepeGil.getEventos().get(0).generarSugerencias(meteorologoAccuweather);
    }

    /* Testea que si el evento esta proximo, las sugerencia se realizan */
    @Test
    public void verificarQueSeHacenSugerencias(){
        LocalDateTime fechaCumpleWilly = LocalDateTime.now();
        panchoPepeGil.crearEvento("Cumpleaños de juan", fechaCumpleWilly, FrecuenciaEvento.ANUAL,"Casa de Juan");

        AccuWeather meteorologoAccuweather = mock(AccuWeather.class);
        TemperaturaAccuWeather temperaturaImpostora= mock(TemperaturaAccuWeather.class);
        temperaturaImpostora.setValue(30);
        when(meteorologoAccuweather.obtenerClima()).thenReturn(temperaturaImpostora);
        panchoPepeGil.getEventos().get(0).generarSugerencias(meteorologoAccuweather);
        Assert.assertTrue(panchoPepeGil.getEventos().get(0).existenSugerencias());
    }


}
