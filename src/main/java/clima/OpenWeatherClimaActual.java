package clima;

public class OpenWeatherClimaActual implements Clima {

    String WeatherText;
    String WeatherIcon;
    TemperaturaAccuWeather Temperature;
    String Coord;
    String Weather;
    String Base;
    String Main;
    String Visibility;
    String clouds;
    String Dt;
    String Sys;

    public OpenWeatherClimaActual(String WeatherText, String WeatherIcon, TemperaturaAccuWeather Temperature) {

        this.WeatherText=WeatherText;
        this.WeatherIcon=WeatherIcon;
        this.Temperature=Temperature;

    }

    public String getWeatherText() {
        return WeatherText;
    }

    public void setWeatherText(String weatherText) {
        WeatherText = weatherText;
    }

    public String getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        WeatherIcon = weatherIcon;
    }

    public TemperaturaAccuWeather getTemperature() {
        //Aca va la logica para convertir lo que te devuelve el JSON de OpenWeather al tipo Temperature
        return Temperature;
    }

    public void setTemperature(TemperaturaAccuWeather temperature) {
        Temperature = temperature;
    }

    public String getCoord() {
        return Coord;
    }

    public void setCoord(String coord) {
        Coord = coord;
    }

    public String getWeather() {
        return Weather;
    }

    public void setWeather(String weather) {
        Weather = weather;
    }

    public String getBase() {
        return Base;
    }

    public void setBase(String base) {
        Base = base;
    }

    public String getMain() {
        return Main;
    }

    public void setMain(String main) {
        Main = main;
    }

    public String getVisibility() {
        return Visibility;
    }

    public void setVisibility(String visibility) {
        Visibility = visibility;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getDt() {
        return Dt;
    }

    public void setDt(String dt) {
        Dt = dt;
    }

    public String getSys() {
        return Sys;
    }

    public void setSys(String sys) {
        Sys = sys;
    }
}
