package persistencia;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.guardarropas.Guardarropas;


public class TestsPersistenciaGuardarropas  extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void SePudeRecuperarUnGuardarropasPersistido() {
		Guardarropas guardaropas = entityManager().find(Guardarropas.class, 1L);
		assertNotNull(guardaropas);
	}

}
