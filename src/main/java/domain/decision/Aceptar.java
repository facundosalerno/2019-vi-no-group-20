package domain.decision;

import domain.atuendo.Atuendo;
import domain.atuendo.Estado;

public class Aceptar extends Decision {

    public Aceptar(Atuendo atuendo){
        this.atuendo=atuendo;
        atuendo.cambiarEstado(Estado.ACEPTADO);
    }

    public void deshacer(){
        atuendo.cambiarEstado(Estado.NUEVO);
    }
}
