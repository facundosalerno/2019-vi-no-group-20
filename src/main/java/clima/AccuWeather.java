package clima;

import com.google.gson.*;

import conexionesUrl.NetClientGet;


public class AccuWeather implements ApiExterna {
    private final String URI = "http://dataservice.accuweather.com/currentconditions/v1/7894?apikey=HV798EVNbOimEhbHAHcvlUkqLs3MwO7N";
    private TemperaturaAccuWeather climaActual;
    private String jsonData;

    public void obtenerClima() {
        jsonData = NetClientGet.main(URI);
        final Gson gson = new Gson();

        JsonParser parser = new JsonParser();
        JsonArray arrayParseado = parser.parse(jsonData).getAsJsonArray();
        JsonElement primerElentoDelArray = arrayParseado.get(0);
        JsonObject objetoParseado = (JsonObject)parser.parse(primerElentoDelArray.toString());

        JsonElement Temperature = objetoParseado.get("Temperature");
        JsonObject element3 = (JsonObject)parser.parse(Temperature.toString());
        JsonElement Metric = element3.get("Metric");

	    this.climaActual = gson.fromJson(Metric, TemperaturaAccuWeather.class);
    }


    public TemperaturaAccuWeather devolverClima() {

        return climaActual;
    }
}
    
