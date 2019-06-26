package domain;

import domain.atuendo.Atuendo;
import domain.guardarropas.GuardarropasLimitado;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import domain.usuario.TipoDeUsuario;
import domain.usuario.Usuario;
import exceptions.ElGuardarropasNoEsAptoException;
import exceptions.NoAceptaCantidadPrendasException;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TestsGuardarropasVariados {
    private Atuendo atuendo;
    private Prenda zapatos;
    private Prenda remera;
    private Prenda camisa;
    private Prenda pantalon;
    private Prenda anteojos;

    @Before
    public void init(){
        Color rojo = new Color(255, 0, 0);
        Color verde = new Color(0, 255, 0);
        Color azul = new Color(0, 0, 255);
        Color blanco = new Color(255, 255, 255);

        zapatos = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO);
        remera = armarUnaPrenda(TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS);
        pantalon = armarUnaPrenda(TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA);
        anteojos= armarUnaPrenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, verde, rojo, Trama.LISA);
        camisa = armarUnaPrenda(TipoDePrenda.CAMISA, Material.ALGODON, blanco, rojo, Trama.LISA);
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

    @Test(expected = NoAceptaCantidadPrendasException.class)
    public void verificarGuardarropasLimitadoAPrendasLimitadas(){
        new GuardarropasLimitado(Arrays.asList(remera, remera, remera, remera, remera),
                Arrays.asList(pantalon, pantalon, pantalon, pantalon, pantalon),
                Arrays.asList(zapatos, zapatos, zapatos),
                Arrays.asList(anteojos));
    }

    @Test(expected = ElGuardarropasNoEsAptoException.class)
    public void asignarGuardarropasLimitadoUsuarioPremium(){
        new Usuario(Arrays.asList(new GuardarropasLimitado(Arrays.asList(remera, remera), Arrays.asList(pantalon, pantalon, pantalon), Arrays.asList(zapatos, zapatos, zapatos), Arrays.asList(anteojos))), TipoDeUsuario.PREMIUM);
    }

    @Test (expected = ElGuardarropasNoEsAptoException.class)
    public void asignarGuardarropasPremiumUsuarioGratis(){
        new Usuario(Arrays.asList(new GuardarropasPremium(Arrays.asList(remera, remera), Arrays.asList(pantalon, pantalon, pantalon), Arrays.asList(zapatos, zapatos, zapatos), Arrays.asList(anteojos))), TipoDeUsuario.GRATIS);
    }
}
