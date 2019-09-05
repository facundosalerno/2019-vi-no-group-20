package domain.decision;

import domain.atuendo.Atuendo;
import domain.atuendo.Estado;

public class Calificar extends Decision{
    public Calificar(Atuendo atuendo, int calificacion){
        this.atuendo=atuendo;
        atuendo.calificar(calificacion);
        atuendo.cambiarEstado(Estado.CALIFICADO);
    }

    @Override
    public void deshacer() {
        atuendo.cambiarEstado(Estado.ACEPTADO);
    }
}
