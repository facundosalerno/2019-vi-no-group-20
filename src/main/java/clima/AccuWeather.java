package clima;

import com.google.gson.Gson;
import conexionesUrl.NetClientGet;

public class AccuWeather implements ApiExterna {
    private final String URI = "http://dataservice.accuweather.com/currentconditions/v1/7894?apikey=HV798EVNbOimEhbHAHcvlUkqLs3MwO7N";
    private ClimaActual climaActual;
    private String jsonData;

    public void obtenerClima() {
        jsonData = NetClientGet.main(URI);
        final Gson gson = new Gson();
        climaActual = gson.fromJson(jsonData, ClimaActual.class);
    }

    public ClimaActual devolverClima() {
       return climaActual;
    }
}
