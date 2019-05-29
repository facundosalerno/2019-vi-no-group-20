package clima;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import conexionesUrl.NetClientGet;

public class OpenWeather implements ApiExterna{
    private final String URI = "https://api.openweathermap.org/data/2.5/weather?q=Buenos%20Aires,%20AR&appid=a4eb7e978f43ec94e5cf3885b5dc3b2c";
    private TemperaturaOpenWeather climaActual;
    private String jsonData;

    public void obtenerClima() {
        jsonData = NetClientGet.main(URI);    
      
        final Gson gson = new Gson();
		
		JsonParser parser = new JsonParser();
		JsonObject element = (JsonObject)parser.parse(gson.toString());

		JsonElement responseWrapper = element.get("main");

	    final TemperaturaOpenWeather climaActual = gson.fromJson(jsonData, TemperaturaOpenWeather.class);
    }

    public TemperaturaOpenWeather devolverClima() {
        return climaActual;
    }

}
