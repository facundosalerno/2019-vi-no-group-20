package fixturePersistibles;


import java.util.Arrays;

import domain.usuario.*;


public class Usuarios {


	public Usuario usuarioLimitado() {
		
		return new Usuario("UsuarioLimitado1",Arrays.asList(new GuardarropasFix().GuardarropasLimitado()),TipoDeUsuario.GRATIS);
	}
	
	public Usuario usuarioPremium() {
		
		return new Usuario("UsuarioPremium1",Arrays.asList(new GuardarropasFix().GuardarropasPremium()),TipoDeUsuario.PREMIUM);
	}
	
}
