package domain.decision;

import domain.atuendo.Atuendo;
import domain.atuendo.Estado;

public class Rechazar extends Decision{

    public Rechazar (Atuendo atuendo){
        this.atuendo=atuendo;
        atuendo.cambiarEstado(Estado.RECHAZADO);
    }

    public void deshacer(){
        atuendo.cambiarEstado(Estado.NUEVO);
    }
}