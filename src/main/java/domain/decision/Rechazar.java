package domain.decision;

import domain.atuendo.Atuendo;
import domain.atuendo.Estado;

import javax.persistence.*;


@Entity
@DiscriminatorValue(value="Rechazar")
public class Rechazar extends Decision{

    public Rechazar (Atuendo atuendo){
        this.atuendo=atuendo;
        atuendo.cambiarEstado(Estado.RECHAZADO);
        atuendo.marcarAtuendoSinUso();
    }

    public void deshacer(){
        atuendo.cambiarEstado(Estado.NUEVO);
        atuendo.marcarAtuendoSinUso();
    }
}
