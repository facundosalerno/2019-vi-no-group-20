package domain.temperaturaPrenda;

import clima.Clima;

public class RangoTemperatura extends TemperaturaPrenda{

    double rangoSuperior = 0;
    double rangoInferior = 0;

    public RangoTemperatura(double rangoInferior, double rangoSuperior){
        this.rangoInferior = rangoInferior;
        this.rangoSuperior = rangoSuperior;
    }


    @Override /* Se adapta solo si esta dentro del rango */
    public boolean seAdapta(Clima climaActual) {
        double temperatura = climaActual.getTemperature();
        return temperatura >= rangoInferior && temperatura <= rangoSuperior;
    }
}
