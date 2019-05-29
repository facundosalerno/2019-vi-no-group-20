package JSON;

import static org.junit.Assert.*;

import org.junit.Test;

import clima.AccuWeather;
import clima.TemperaturaAccuWeather;



public class TestsJsonTemperaturaDesdeAccuWeather {

	
	@Test
	public void debeDevolverJSONEnUnObjetoAccuWeather() {
		
		AccuWeather nuevaConexion= new AccuWeather();
		TemperaturaAccuWeather nuevoClima= nuevaConexion.devolverClima();
		assertEquals(12.8, nuevoClima.getTemperature());
	}
}
