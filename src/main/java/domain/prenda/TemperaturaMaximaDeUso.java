package domain.prenda;

import clima.Clima;
import domain.capaPrenda.NivelDeCapa;

public class TemperaturaMaximaDeUso {
    private int temperaturaMaximaDeUso;
    private NivelDeCapa nivelDeCapa;

    public TemperaturaMaximaDeUso(int temperatura){
        temperaturaMaximaDeUso = temperatura;
    }

    public int getTemperaturaMaximaDeUso(){
        return temperaturaMaximaDeUso;
    }

    public static NivelDeCapa getNivelDeCapa(Clima climaActual){
        int temperatura = (int) climaActual.getTemperature();
        if(temperatura < 5)
            return NivelDeCapa.MUY_ELEVADO;
        else if(temperatura >= 5 && temperatura < 18)
            return NivelDeCapa.ELEVADO;
        else if(temperatura >= 18 && temperatura < 27)
            return  NivelDeCapa.MEDIO;
        else if(temperatura >= 27)
            return NivelDeCapa.LIVIANO;
        return null;
    }

}
