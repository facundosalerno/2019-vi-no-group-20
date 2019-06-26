package domain.prenda;

import exceptions.TemperaturaInvalidaException;

public class TemperaturaAdecuadaPrenda {
    private final int rangoMaximoTeorico = 60;
    private final int getRangoMinimoTeorico = -30;
    private int rangoMaximo;
    private int rangoMinimo;

    public TemperaturaAdecuadaPrenda(int rangoMinimo, int rangoMaximo){
        if((this.rangoMaximo = rangoMaximo) > rangoMaximoTeorico)
            throw new TemperaturaInvalidaException("Vaya, esa temperatura es muy alta");
        if((this.rangoMinimo = rangoMinimo) < getRangoMinimoTeorico)
            throw new TemperaturaInvalidaException("Vaya, esa temperatura es muy baja");
        if(rangoMaximo < rangoMinimo)
            throw new TemperaturaInvalidaException("El rango maximo deberia ser mayor que el rango minimo");
    }


    public boolean temperaturaSeEncuentraEnElRango(int temperatura){
            return temperatura > rangoMinimo && temperatura < rangoMaximo;
    }

    public int getRangoMaximo(){
        return rangoMaximo;
    }

    static int getRangoMaximoTeorico(){
        return getRangoMaximoTeorico();
    }
}
