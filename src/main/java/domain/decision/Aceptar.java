package domain;

public class Aceptar extends Decision {

    public Aceptar(Atuendo atuendo){
        this.atuendo=atuendo;
        atuendo.cambiarEstado(Estado.ACEPTADO);
    }

    public void deshacer(){
        atuendo.cambiarEstado(Estado.NUEVO);
    }
}
