package clima;

public class Temperature {
	SistemaUnidades Metric;
	SistemaUnidades Imperial;
	
	
	public Temperature(SistemaUnidades Metric,SistemaUnidades Imperial) {
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
}
