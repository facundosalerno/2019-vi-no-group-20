package dominio.scheduler.quartz;

/**
 * 
 * Este builder existe para crear un IntervaloQuartz usando valores por defecto
 * en caso de que no se requiera completar todos los parametros necesarios.
 * 
 * El IntervaloQuartz que crea por defecto es uno que se ejecuta todos los dias
 * al momento 0:0:0 de cada dia
 *
 */
public class IntervaloQuartzBuilder {

	private String segundo = "0";
	private String minuto = "0";
	private String hora = "0";
	private String diaDelMes = "*";
	private String mes = "*";
	private String diaDeLaSemana = "?";
	private String anio = "";

	// TODO: Hacer excepcion en InterValoQuartz para validar estos valores
	public IntervaloQuartz create() {
		return new IntervaloQuartz(segundo, minuto, hora, diaDelMes, mes, diaDeLaSemana, anio);
	}

	public IntervaloQuartzBuilder setSegundo(String segundo) {
		this.segundo = segundo;
		return this;
	}

	public IntervaloQuartzBuilder setMinuto(String minuto) {
		this.minuto = minuto;
		return this;
	}

	public IntervaloQuartzBuilder setHora(String hora) {
		this.hora = hora;
		return this;
	}

	public IntervaloQuartzBuilder setDiaDelMes(String diaDelMes) {
		this.diaDelMes = diaDelMes;
		return this;
	}

	public IntervaloQuartzBuilder setMes(String mes) {
		this.mes = mes;
		return this;
	}

	public IntervaloQuartzBuilder setDiaDeLaSemana(String diaDeLaSemana) {
		this.diaDeLaSemana = diaDeLaSemana;
		return this;
	}

	public IntervaloQuartzBuilder setAnio(String anio) {
		this.anio = anio;
		return this;
	}
}
