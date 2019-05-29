package domain;

import exceptions.TemperaturaInvalidaException;

public class TemperaturaAdecuadaPrenda {
    private int rangoMaximo;
    private int rangoMinimo;
    private boolean importaLaTemperatura; //Para los accesorios por ejemplo

    public TemperaturaAdecuadaPrenda(int rangoMinimo, int rangoMaximo){
        if((this.rangoMaximo = rangoMaximo) > 60)
            throw new TemperaturaInvalidaException("Vaya, esa temperatura es muy alta");
        if((this.rangoMinimo = rangoMinimo) < -30)
            throw new TemperaturaInvalidaException("Vaya, esa temperatura es muy baja");
        if(rangoMaximo < rangoMinimo)
            throw new TemperaturaInvalidaException("El rango maximo deberia ser mayor que el rango minimo");
        importaLaTemperatura = true;
    }

    public TemperaturaAdecuadaPrenda(){
        importaLaTemperatura = false;
    }

    public boolean temperaturaSeEncuentraEnElRango(int temperatura){
        if(importaLaTemperatura)
            return temperatura > rangoMinimo && temperatura < rangoMaximo;
        else
            return true;
    }

    public int getRangoMaximo(){
        return rangoMaximo;
    }
}
