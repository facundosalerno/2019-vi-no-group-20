package domain.decision;

import domain.atuendo.Atuendo;
import domain.atuendo.Estado;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="Aceptar")
public class Aceptar extends Decision {

    public Aceptar(Atuendo atuendo){
        this.atuendo=atuendo;
        atuendo.cambiarEstado(Estado.ACEPTADO);
    }

    public void deshacer(){
        atuendo.cambiarEstado(Estado.NUEVO);
    }
}
