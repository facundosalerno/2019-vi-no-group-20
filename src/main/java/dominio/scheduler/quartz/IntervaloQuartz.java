package dominio.scheduler.quartz;

import dominio.scheduler.Intervalo;
//TODO: Agregar un builder para esto
public class IntervaloQuartz implements Intervalo {

	private String segundo;
	private String minuto;
	private String hora;
	private String diaDelMes;
	private String mes;
	private String diaDeLaSemana;
	private String anio;


	/**
	 * Genera la expresion para Quartz para el dia 1 a las 00:00:00 de todos los meses y todos los anios 
	 */
	public IntervaloQuartz() {
		segundo = "0";  
		minuto = "0";   
		hora = "0";     
		diaDelMes = "*";
		mes = "*";       
		diaDeLaSemana = "?";  
		anio = "";           
	}
	

	
	/**
	 * Genera la expresion para el intervalo para Quartz
	 *
	 * @param segundo			mandatory = yes. allowed values = {@code  0-59    * / , -}
	 * @param minuto			mandatory = yes. allowed values = {@code  0-59    * / , -}
	 * @param hora				mandatory = yes. allowed values = {@code 0-23   * / , -}
	 * @param diaDelMes			mandatory = yes. allowed values = {@code 1-31  * / , - ? L W}
	 * @param mes				mandatory = yes. allowed values = {@code 1-12 or JAN-DEC    * / , -}
	 * @param diaDeLaSemana		mandatory = yes. allowed values = {@code 0-6 or SUN-SAT * / , - ? L #}
	 * @param anio				mandatory = no. allowed values = {@code 1970–2099    * / , -}
	 * @return la expresion del intervalo para Quartz.
	 */
	public IntervaloQuartz(String segundo, String minuto, String hora, String diaDelMes, String mes,
			String diaDeLaSemana, String anio) {
		this.segundo = segundo;
		this.minuto = minuto;
		this.hora = hora;
		this.diaDelMes = diaDelMes;
		this.mes = mes;
		this.diaDeLaSemana = diaDeLaSemana;
		this.anio = anio;
	}



	@Override
	public String obtenerComoString() {
		return String.format("%1$s %2$s %3$s %4$s %5$s %6$s %7$s", segundo, minuto, hora, diaDelMes, mes, diaDeLaSemana, anio);
	}

}
