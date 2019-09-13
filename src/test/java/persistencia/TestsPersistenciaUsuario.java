package persistencia;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.usuario.Usuario;
import fixturePersistibles.Usuarios;
import fixturePersistibles.GuardarropasFix;


public class TestsPersistenciaUsuario  extends AbstractPersistenceTest implements WithGlobalEntityManager {

	public void setup() {
		super.setup();
		new TestsPersistorDominio().obtenerDominioPersistido();
	}
	
		
	@Test
	public void SePudeRecuperarUnUsuarioPersistido() {
		Usuario usu = entityManager().find(Usuario.class, 1L);
		assertNotNull(usu);
	}
	
	/*
	@Test
	public void SePudeRecuperarOtroUsuarioPersistido() {
		Usuario usu = entityManager().find(Usuario.class, 2L);
		assertNotNull(usu);
	}
	*/
	
	@Test
	public void SePuedeModificarElguardarropasDeUnUsuario() {
		Usuario usu = entityManager().find(Usuario.class, 1L);
		usu.getGuardarropas(0).agregarPrendaSuperior(GuardarropasFix.getCamisa());
		
		Usuario otraInstanciaUsu = entityManager().find(Usuario.class, 1L);
		assertEquals(usu.getGuardarropas(0).getPrendasSuperiores().size(),3);
		
	}
	

}
