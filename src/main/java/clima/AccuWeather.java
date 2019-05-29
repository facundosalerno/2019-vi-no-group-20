package clima;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import conexionesUrl.NetClientGet;

public class AccuWeather implements ApiExterna {
    private final String URI = "http://dataservice.accuweather.com/currentconditions/v1/7894?apikey=HV798EVNbOimEhbHAHcvlUkqLs3MwO7N";
    private TemperaturaAccuWeather climaActual;
    private String jsonData;

    public void obtenerClima() {
        jsonData = NetClientGet.main(URI);
        final Gson gson = new Gson();
		
		JsonParser parser = new JsonParser();
		JsonObject element = (JsonObject)parser.parse(gson.toString());

		JsonElement responseWrapper = element.get("Metric");

	    final TemperaturaAccuWeather climaActual = gson.fromJson(jsonData, TemperaturaAccuWeather.class);
    }

    public TemperaturaAccuWeather devolverClima() {

        return climaActual;
    }
}
    
