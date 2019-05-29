package clima;

public class TemperaturaAccuWeather implements Clima{
	float Value;
	String Unit;
	String UnitType;
	
	
	public float getValue() {
		return Value;
	}
	public void setValue(float value) {
		Value = value;
	}
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
	public float getTemperature() {
		
		return this.getValue();
	}
}

