package domain;

import static org.junit.Assert.*;

import domain.capaPrenda.Capa;
import domain.capaPrenda.CapaCompuesta;
import domain.capaPrenda.CapaSimple;
import domain.prenda.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.capaPrenda.NivelDeCapa;

import java.util.Arrays;

public class TestNiveldeCapa {

	private Prenda prendaZapatos;
	private Prenda prendaRemera;
	private Prenda prendaBuso;
	private Prenda prendaCampera;
	private Prenda prendaPantalon;
	private Prenda prendaAnteojos;

	private CapaSimple zapatos;
	private CapaSimple remera;
	private CapaSimple buso;
	private CapaSimple campera;
	private CapaSimple pantalon;
	private CapaSimple anteojos;
	private CapaCompuesta parteSuperiorInvierno;

	@Before
	public void init() {
		//Instanciaciones previas a los TEST
		Color rojo = new Color(255, 0, 0);
		Color verde = new Color(0, 255, 0);
		Color azul = new Color(0, 0, 255);

		prendaZapatos = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO);
		prendaRemera = armarUnaPrenda(TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS);
		prendaPantalon = armarUnaPrenda(TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA);
		prendaAnteojos = armarUnaPrenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, verde, rojo, Trama.LISA);
		prendaBuso = armarUnaPrenda(TipoDePrenda.BUSO, Material.ALGODON, azul, verde, Trama.LISA);
		prendaCampera = armarUnaPrenda(TipoDePrenda.CAMPERA, Material.JEAN, verde, null, Trama.GASTADO);


		zapatos = new CapaSimple(prendaZapatos);
		remera = new CapaSimple(prendaRemera);
		buso = new CapaSimple(prendaBuso);
		pantalon = new CapaSimple(prendaPantalon);
		anteojos = new CapaSimple(prendaAnteojos);

		parteSuperiorInvierno = new CapaCompuesta(Arrays.asList(remera, buso, campera));

	}

	public Prenda armarUnaPrenda(TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama) {
		BorradorPrenda borradorPrenda = new BorradorPrenda();
		borradorPrenda.definirTipo(tipoDePrenda);
		borradorPrenda.definirMaterial(material);
		borradorPrenda.definirColorPrimario(colorPrimario);
		if(colorSecundario != null) {

			borradorPrenda.definirColorSecundario(colorSecundario);
		}
		borradorPrenda.definirTrama(trama);
		return borradorPrenda.crearPrenda();
	}
	@Test
	public  void ordinalNivelCapa(){
		
		Assert.assertEquals(NivelDeCapa.MEDIO.ordinal(),1);

	}

	//TODO:Test para verificar que las capas estan ordenadas
	@Test
	public void capasEstanOrdenadas(){
		Assert.assertTrue(parteSuperiorInvierno.estanOrdenadas());

	}
	//TODO:Test para verificar que se dispara una excepcion si no hay capas creadas debido a que no se satisface la temperatura
	@Test
	public void generarAtuendoTiraExcepcionSiNohayCapasQueSatisfaganLaTemperatura(){

	}
}
