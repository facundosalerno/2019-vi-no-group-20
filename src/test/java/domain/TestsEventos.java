package domain;

import clima.AccuWeather;
import clima.Meteorologo;
import clima.TemperaturaAccuWeather;
import exceptions.TodaviaNoEstaCercaElEvento;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestsEventos {
    private Usuario panchoPepeGil;
    private Evento cumpleañosDeWillyWonka;
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
        LocalDateTime fechaCumpleWilly= LocalDateTime.of(2020,06,20,20,30);
        cumpleañosDeWillyWonka = new Evento("Cumpleaños de juan", fechaCumpleWilly,"Casa de Juan", panchoPepeGil);
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

    @Test(expected = TodaviaNoEstaCercaElEvento.class)
    public void verificarQueSoloSeHacenSugerenciasAlEstarProximoElEvento(){
        AccuWeather meteorologoAccuweather = mock(AccuWeather.class);
        TemperaturaAccuWeather temperaturaImpostora= mock(TemperaturaAccuWeather.class);
        temperaturaImpostora.setValue(20);
        when(meteorologoAccuweather.obtenerClima()).thenReturn(temperaturaImpostora);

        cumpleañosDeWillyWonka.generarSugerencias(meteorologoAccuweather);
    }


}
