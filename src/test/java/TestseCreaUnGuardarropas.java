package persistencia;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.guardarropas.Guardarropas;
import fixturePersistibles.GuardarropasFix;


public class TestseCreaUnGuardarropas {

	@Test 
	public void SeCreaUnGuardarropasLimitado() {
		Guardarropas guarda =  new GuardarropasFix().GuardarropasLimitado();
		assertNotNull(guarda);
	}
	
	
	@Test 
	public void SeCreaUnGuardarropasPremium() {
		Guardarropas guarda =  new GuardarropasFix().GuardarropasPremium();
		assertNotNull(guarda);
	}
	
	@Test 
	public void SeAgregaUnaPrendaUnGuardarropasPremium() {
		Guardarropas guarda =  new GuardarropasFix().GuardarropasPremium();
		guarda.agregarPrendaSuperior(GuardarropasFix.getCamisa());
		assertEquals(guarda.getPrendasSuperiores().size(),3);
	}

}
