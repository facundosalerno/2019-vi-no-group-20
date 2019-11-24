package domain;

import domain.atuendo.Atuendo;
import domain.atuendo.Estado;
import domain.guardarropas.Guardarropas;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import domain.usuario.TipoDeUsuario;
import domain.usuario.Usuario;
import net.sf.oval.guard.Pre;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class TestsDecisiones {
    private Usuario batman;
    private Guardarropas guardarropasVeranoBatman;
    private Atuendo trajeBatman;
    private Prenda prendaBotaNegra;
    private Prenda prendaArmaduraNegra;
    private Prenda prendaPantalonNegro;
    private Prenda prendaAntifazDeMurcielago;
    private Prenda botaNegra;
    private Prenda armaduraNegra;
    private Prenda pantalonNegro;
    private Prenda antifazDeMurcielago;

    @Before
    public void init(){
        Color negro = new Color(0, 0, 0);

        botaNegra = armarUnaPrenda("Bota Negra",TipoDePrenda.ZAPATO, Material.CUERO, negro, Trama.GASTADO);
        armaduraNegra = armarUnaPrenda("Armadura Negra",TipoDePrenda.REMERA, Material.ALGODON, negro, Trama.CUADROS);
        pantalonNegro = armarUnaPrenda("Pantalon Negro",TipoDePrenda.PANTALON, Material.JEAN, negro, Trama.RAYADA);
        antifazDeMurcielago = armarUnaPrenda("Antifaz de murcielago",TipoDePrenda.ANTEOJOS, Material.PLASTICO, negro, Trama.LISA);

        trajeBatman = new Atuendo(Arrays.asList(armaduraNegra), pantalonNegro, botaNegra, antifazDeMurcielago);

        guardarropasVeranoBatman = new GuardarropasPremium("Guardarropas de verano", Arrays.asList(prendaArmaduraNegra), Arrays.asList(prendaPantalonNegro), Arrays.asList(prendaBotaNegra), Arrays.asList(prendaAntifazDeMurcielago));

        batman = new Usuario("Facundo Salerno",Arrays.asList(guardarropasVeranoBatman), TipoDeUsuario.PREMIUM);
    }

    public Prenda armarUnaPrenda(String nombre, TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Trama trama){
        BorradorPrenda borradorPrenda = new BorradorPrenda();
        borradorPrenda.definirNombre(nombre);
        borradorPrenda.definirTipo(tipoDePrenda);
        borradorPrenda.definirMaterial(material);
        borradorPrenda.definirColorPrimario(colorPrimario);
        borradorPrenda.definirTrama(trama);
        return borradorPrenda.crearPrenda();
    }

    @Test
    public void verificarQueElAtuendoEsAceptado(){
        batman.aceptarSugerencia(trajeBatman);
        Assert.assertEquals(trajeBatman.getEstado(), Estado.ACEPTADO);
    }

    @Test
    public void verificarQueLaDecisionSePuedeDeshaer(){
        batman.aceptarSugerencia(trajeBatman);
        batman.deshacerUltimaDecision();
        Assert.assertEquals(trajeBatman.getEstado(), Estado.NUEVO);
    }

}
