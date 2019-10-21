package persis;

import domain.evento.FrecuenciaEvento;
import domain.guardarropas.Guardarropas;
import domain.guardarropas.GuardarropasLimitado;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import domain.usuario.TipoDeUsuario;
import domain.usuario.Usuario;
import org.eclipse.core.runtime.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class TestUsuarios extends AbstractPersistenceTest implements WithGlobalEntityManager {
    private Usuario facundo;

    private GuardarropasPremium guardarropasCasual;
    private GuardarropasLimitado guardarropasLimitado;
    private Prenda zapatos;
    private Prenda remera;
    private Prenda camisa;
    private Prenda pantalon;
    private Prenda anteojos;

    LocalDateTime fechaCumpleWilly;
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


        fechaCumpleWilly = LocalDateTime.of(2020,06,20,20,30);

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
    public void sePersisteUnUsuarioConSuGuardarropas(){
        facundo = new Usuario("Facundo Salerno",new ArrayList<Guardarropas>(Arrays.asList(new GuardarropasPremium("guardarropas casual", Arrays.asList(remera, camisa), Arrays.asList(pantalon), Arrays.asList(zapatos), Arrays.asList(anteojos)))), TipoDeUsuario.PREMIUM);
        guardarropasCasual = new GuardarropasPremium("guardarropas casual", new ArrayList<Prenda>(Arrays.asList(camisa, remera)), new ArrayList<Prenda>(Arrays.asList(pantalon)), new ArrayList<Prenda>(Arrays.asList(zapatos)), new ArrayList<Prenda>(Arrays.asList(anteojos)));

        facundo.agregarGuardarropas(guardarropasCasual);

        entityManager().persist(facundo);

        entityManager().find(Usuario.class,facundo.getId());
    }

    @Test
    public void sePersisteUnUsuarioConUnEvento(){
        facundo = new Usuario("Facundo Salerno",new ArrayList<Guardarropas>(Arrays.asList(new GuardarropasPremium("guardarropas casual", Arrays.asList(remera, camisa), Arrays.asList(pantalon), Arrays.asList(zapatos), Arrays.asList(anteojos)))), TipoDeUsuario.PREMIUM);
        facundo.crearEvento("Cumpleaños de juan", fechaCumpleWilly, FrecuenciaEvento.ANUAL,"Casa de Juan");
        entityManager().persist(facundo);

        entityManager().find(Usuario.class,facundo.getId());
    }
}
