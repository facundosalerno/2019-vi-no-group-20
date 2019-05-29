package clima;

public class AccuWeatherClimaActual implements Clima{


	String LocalObservationDateTime ;
	String EpochTime ;
	String WeatherText;
	String WeatherIcon;
	String HasPrecipitation;
	String PrecipitationType;
	String IsDayTime;
	TemperaturaAccuWeather Temperature;
	String MobileLink;
	String Link;

	public AccuWeatherClimaActual(String LocalObservationDateTime, String EpochTime, String WeatherText, String WeatherIcon, String HasPrecipitation,String PrecipitationType,String IsDayTime,TemperaturaAccuWeather Temperature,String MobileLink,String Link) {

		this.LocalObservationDateTime=LocalObservationDateTime ;
		this.EpochTime=EpochTime ;
		this.WeatherText=WeatherText;
		this.WeatherIcon=WeatherIcon;
		this.HasPrecipitation=HasPrecipitation;
		this.PrecipitationType=PrecipitationType;
		this.IsDayTime=IsDayTime;
		this.Temperature=Temperature;
		this.MobileLink=MobileLink;
		this.Link=Link;

	}

	public String getLocalObservationDateTime() {
		return LocalObservationDateTime;
	}

	public void setLocalObservationDateTime(String localObservationDateTime) {
		LocalObservationDateTime = localObservationDateTime;
	}

	public String getEpochTime() {
		return EpochTime;
	}

	public void setEpochTime(String epochTime) {
		EpochTime = epochTime;
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

	public String getHasPrecipitation() {
		return HasPrecipitation;
	}

	public void setHasPrecipitation(String hasPrecipitation) {
		HasPrecipitation = hasPrecipitation;
	}

	public String getPrecipitationType() {
		return PrecipitationType;
	}

	public void setPrecipitationType(String precipitationType) {
		PrecipitationType = precipitationType;
	}

	public String getIsDayTime() {
		return IsDayTime;
	}

	public void setIsDayTime(String isDayTime) {
		IsDayTime = isDayTime;
	}

	public TemperaturaAccuWeather getTemperature() {
		return Temperature;
	}

	public void setTemperature(TemperaturaAccuWeather temperature) {
		Temperature = temperature;
	}

	public String getMobileLink() {
		return MobileLink;
	}

	public void setMobileLink(String mobileLink) {
		MobileLink = mobileLink;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}
}
