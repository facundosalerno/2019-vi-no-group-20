package JSON;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;

import clima.AccuWeatherClimaActual;

public class TestsJsonTemperatura {

	
	@Test
	public void debeDevolverJSONEnUnObjetoAccuWeather() {
		final String json = "{\r\n" + 
				"        \"LocalObservationDateTime\": \"2019-05-23T14:20:00-03:00\",\r\n" + 
				"        \"EpochTime\": 1558632000,\r\n" + 
				"        \"WeatherText\": \"Soleado\",\r\n" + 
				"        \"WeatherIcon\": 1,\r\n" + 
				"        \"HasPrecipitation\": false,\r\n" + 
				"        \"PrecipitationType\": null,\r\n" + 
				"        \"IsDayTime\": true,\r\n" + 
				"        \"Temperature\": {\r\n" + 
				"            \"Metric\": {\r\n" + 
				"                \"Value\": 16.1,\r\n" + 
				"                \"Unit\": \"C\",\r\n" + 
				"                \"UnitType\": 17\r\n" + 
				"            },\r\n" + 
				"            \"Imperial\": {\r\n" + 
				"                \"Value\": 61,\r\n" + 
				"                \"Unit\": \"F\",\r\n" + 
				"                \"UnitType\": 18\r\n" + 
				"            }\r\n" + 
				"        },\r\n" + 
				"        \"MobileLink\": \"http://m.accuweather.com/es/ar/buenos-aires/7894/current-weather/7894\",\r\n" + 
				"        \"Link\": \"http://www.accuweather.com/es/ar/buenos-aires/7894/current-weather/7894\"\r\n" + 
				"    }";
		final Gson gson = new Gson();
	    final AccuWeatherClimaActual clima = gson.fromJson(json, AccuWeatherClimaActual.class);
	    assertEquals("2019-05-23T14:20:00-03:00", clima.getLocalObservationDateTime());
		assertEquals("1558632000", clima.getEpochTime());
		assertEquals("Soleado", clima.getWeatherText());
		assertEquals("1", clima.getWeatherIcon());
		assertEquals("false", clima.getHasPrecipitation());
		assertEquals(null, clima.getPrecipitationType());
		assertEquals("true", clima.getIsDayTime());
		assertEquals("16.1", clima.getTemperature().getMetric().getValue());
		assertEquals("C", clima.getTemperature().getMetric().getUnit());
		assertEquals("17", clima.getTemperature().getMetric().getUnitType());
		assertEquals("61", clima.getTemperature().getImperial().getValue());
		assertEquals("F", clima.getTemperature().getImperial().getUnit());
		assertEquals("18", clima.getTemperature().getImperial().getUnitType());
	}

}
