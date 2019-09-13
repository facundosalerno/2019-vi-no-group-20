package fixturePersistibles;


import domain.prenda.*;
import domain.usuario.*;
import domain.guardarropas.*;




public class Persistibles {
	private static Persistibles instance = null;
	private Usuario usuarioLimitado;
	private Usuario usuarioPremium;
	private GuardarropasFix guardarropas;
	private Prenda prenda;
	private TipoDePrenda tipoDeprenda;
	
	protected Persistibles() {
		super();
		this.prepararEntidadesPersistibles();
	}
	
	public static Persistibles getInstance() {
		if (instance == null)
			instance = new Persistibles();
		return instance;
	}
	
	private void prepararEntidadesPersistibles() {
		
		usuarioLimitado = new Usuarios().usuarioLimitado();
		usuarioPremium = new Usuarios().usuarioPremium();
		
	}

	public Usuario getUsuarioLimitado() {
		return usuarioLimitado;
	}
	
	public Usuario getUsuarioPremium() {
		return usuarioPremium;
	}
}
