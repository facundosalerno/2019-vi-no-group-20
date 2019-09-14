package domain.temperaturaPrenda;

import clima.Clima;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RangoTemperatura)) return false;
        RangoTemperatura that = (RangoTemperatura) o;
        return Double.compare(that.rangoSuperior, rangoSuperior) == 0 &&
                Double.compare(that.rangoInferior, rangoInferior) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rangoSuperior, rangoInferior);
    }
}
