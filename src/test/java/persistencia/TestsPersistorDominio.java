package persistencia;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import domain.usuario.Usuario;
import fixturePersistibles.Persistibles;

public class TestsPersistorDominio implements WithGlobalEntityManager {

	public Persistibles obtenerDominioPersistido() {
		if (!this.fixturePersistido())
			this.dbSetup();
		return Persistibles.getInstance();
	}

	private Boolean fixturePersistido() {
		return (entityManager().find(Usuario.class, 1L) == null);
	}

	private void dbSetup() {
		Usuario usu1= Persistibles.getInstance().getUsuarioLimitado();
		//Usuario usu2= Persistibles.getInstance().getUsuarioPremium();
		entityManager().persist(usu1);
		//entityManager().persist(usu2);
		entityManager().getTransaction().commit();
		
	}
}
