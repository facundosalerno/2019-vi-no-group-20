package persistencia;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.evento.Evento;

//TODO: Crear en el fixture los eventos necesarios para persistir
public class TestsPersistenciaEvento  extends AbstractPersistenceTest implements WithGlobalEntityManager{

	@Test
	public void SePudeRecuperarUnEventoPersistido() {
		Evento evento = entityManager().find(Evento.class, 1L);
		assertNotNull(evento);
	}
}
