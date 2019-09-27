package persis;

import domain.evento.FrecuenciaEvento;
import domain.guardarropas.GuardarropasLimitado;
import domain.guardarropas.GuardarropasPremium;
import domain.prenda.*;
import domain.usuario.TipoDeUsuario;
import domain.usuario.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDateTime;
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


        LocalDateTime fechaCumpleWilly = LocalDateTime.of(2020,06,20,20,30);
        //facundo.crearEvento("Cumpleaños de juan", fechaCumpleWilly, FrecuenciaEvento.ANUAL,"Casa de Juan");
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
        facundo = new Usuario("Facundo Salerno",Arrays.asList(new GuardarropasPremium(Arrays.asList(remera, camisa), Arrays.asList(pantalon), Arrays.asList(zapatos), Arrays.asList(anteojos))), TipoDeUsuario.PREMIUM);
        guardarropasCasual = new GuardarropasPremium(Arrays.asList(camisa, remera), Arrays.asList(pantalon), Arrays.asList(zapatos), Arrays.asList(anteojos));

        facundo.agregarGuardarropas(guardarropasCasual);

        entityManager().persist(facundo);
        /*
        Cancha cancha = new Cancha("Cancha uno", new Color("rojo"));

        Paleta paletaA = new Paleta();
        Paleta paletaB = new Paleta();

        Jugador jose = new JugadorAmateur();
        jose.setPaleta(paletaA);
        Jugador maria = new JugadorProfesional();
        jose.setPaleta(paletaB);
        entityManager().persist(jose);
        entityManager().persist(maria);
        cancha.reservar(LocalDateTime.now(),//
                Arrays.asList(jose, maria));

        repositorioCancha.registrar(cancha);
*/
    }

}
