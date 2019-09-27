package persis;

import domain.atuendo.Atuendo;
import domain.guardarropas.Guardarropas;
import domain.guardarropas.GuardarropasLimitado;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import fixturePersistibles.GuardarropasFix;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestGuardarropas {
    private GuardarropasPremium guardarropasPremium;
    private GuardarropasLimitado guardarropasLimitado;
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

        guardarropasPremium = new GuardarropasPremium(Arrays.asList(camisa, remera), Arrays.asList(pantalon), Arrays.asList(zapatos), Arrays.asList(anteojos));
        guardarropasLimitado = new GuardarropasLimitado(Arrays.asList(camisa, remera), Arrays.asList(pantalon), Arrays.asList(zapatos), Arrays.asList(anteojos));
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

    @Test
    public void SeCreaUnGuardarropasLimitado() {

    }


    @Test
    public void SeCreaUnGuardarropasPremium() {

    }

    @Test
    public void SeAgregaUnaPrendaUnGuardarropasPremium() {

    }
}
