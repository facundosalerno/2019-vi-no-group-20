package persistencia;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.usuario.Usuario;
import fixturePersistibles.Persistibles;
import fixturePersistibles.Usuarios;

public class TestsseCreaUnaInstancia {

	@Test
	public void seCreaUnaInstancia() {
		Persistibles insta =  Persistibles.getInstance();
		assertNotNull(insta);
	}
}
