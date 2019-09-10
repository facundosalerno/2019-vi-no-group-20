package dominio.scheduler;

import dominio.clientes.Cliente;
import dominio.recomendador.Recomendacion;
import dominio.recomendador.Recomendador;
//TODO: Agregarle cuando el cliente tenga las reglas de actuar por si solo
public class TareaRecomendador implements Tarea {

	private Recomendador recomendador;
	private Cliente cliente;

	public TareaRecomendador(Recomendador recomendador, Cliente cliente) {
		this.recomendador = recomendador;
		this.cliente = cliente;
	}


	@Override
	public void ejecutar() {
		Recomendacion recomendacion = recomendador.obtenerRecomendacion(cliente.getDispositivosInteligentes());
		cliente.actuarSegun(recomendacion);
	}

}
