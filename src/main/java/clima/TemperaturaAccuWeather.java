package clima;

public class TemperaturaAccuWeather extends Clima{
	String Unit;
	String UnitType;
	
	public TemperaturaAccuWeather (double Value,String Unit,String UnitType) {
		this.temp=Value;
		this.Unit=Unit;
		this.UnitType=UnitType;
		
	}
	
	public double getValue() {
		return temp;
	}
	public void setValue(float value) { temp = value; }
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public String getUnitType() {
		return UnitType;
	}
	public void setUnitType(String unitType) {
		UnitType = unitType;
	}
	
	@Override
	public double getTemperature() {
		
		return this.getValue();
	}
}

