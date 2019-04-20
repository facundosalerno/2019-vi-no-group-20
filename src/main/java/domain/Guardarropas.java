package domain;

import java.util.List;

public class Guardarropas {
	
	List<Prenda> predasSuperior;
	List<Prenda> predasInferior;
	List<Prenda> predasCalzador;

public Atuendo generarSugerencia() {
	Atuendo atuendo;
	while atuendo.atuendoValido();{
	atuendo = combinar(predasSuperior,predasInferior,predasCalzador);
	}
	
	return atuendo;
	
}
}
