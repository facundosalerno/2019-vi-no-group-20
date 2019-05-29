package JSON;

import static org.junit.Assert.*;

import org.junit.Test;

import clima.AccuWeather;
import clima.OpenWeather;
import clima.TemperaturaAccuWeather;
import clima.TemperaturaOpenWeather;



public class TestsJsonTemperatura {

	//verificar con postman las temperaturas antes del hacer el test
	@Test
	public void debeDevolverJSONEnUnObjetoAccuWeather() {
		
		AccuWeather nuevaConexion= new AccuWeather();
		TemperaturaAccuWeather nuevoClima= nuevaConexion.devolverClima();
		assertEquals(12.8, nuevoClima.getTemperature());
	}
	
	@Test
	public void debeDevolverJSONEnUnObjetoOpenWeather() {
		
		OpenWeather nuevaConexion= new OpenWeather();
		TemperaturaOpenWeather nuevoClima= nuevaConexion.devolverClima();
		assertEquals(12.91, nuevoClima.getTemperature());
	}
}
