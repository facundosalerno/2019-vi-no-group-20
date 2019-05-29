package clima;

public class TemperaturaAccuWeather implements Clima{
	SistemaUnidades Metric;
	SistemaUnidades Imperial;
	
	
	public TemperaturaAccuWeather(SistemaUnidades Metric,SistemaUnidades Imperial) {
		this.Metric=Metric;
		this.Imperial=Imperial;
		
	}


	public SistemaUnidades getMetric() {
		return Metric;
	}


	public void setMetric(SistemaUnidades metric) {
		Metric = metric;
	}


	public SistemaUnidades getImperial() {
		return Imperial;
	}


	public void setImperial(SistemaUnidades imperial) {
		Imperial = imperial;
	}


	@Override
	public String getWeatherText() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setWeatherText(String weatherText) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public TemperaturaAccuWeather getTemperature() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setTemperature(TemperaturaAccuWeather temperature) {
		// TODO Auto-generated method stub
		
	}
}
