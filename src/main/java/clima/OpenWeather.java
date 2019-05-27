package clima;

import com.google.gson.Gson;
import conexionesUrl.NetClientGet;

public class OpenWeather implements ApiExterna{
    private final String URI = "https://api.openweathermap.org/data/2.5/weather?q=Buenos%20Aires,%20AR&appid=a4eb7e978f43ec94e5cf3885b5dc3b2c";
    private OpenWeatherClimaActual climaActual;
    private String jsonData;

    public void obtenerClima() {
        jsonData = NetClientGet.main(URI);
        final Gson gson = new Gson();
        climaActual = gson.fromJson(jsonData, OpenWeatherClimaActual.class);
    }

    public OpenWeatherClimaActual devolverClima() {
        return climaActual;
    }

}
