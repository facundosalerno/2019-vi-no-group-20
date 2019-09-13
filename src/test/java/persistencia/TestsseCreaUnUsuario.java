package persistencia;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.usuario.Usuario;
import fixturePersistibles.Usuarios;

public class TestsseCreaUnUsuario {

	@Test
	public void SeCreaUnUsuarioLimitado() {
		Usuario usu =  new Usuarios().usuarioLimitado();
		assertNotNull(usu);
	}
	
	@Test
	public void SeCreaUnUsuarioPremium() {
		Usuario usu =  new Usuarios().usuarioPremium();
		assertNotNull(usu);
	}
	
	
	

}
