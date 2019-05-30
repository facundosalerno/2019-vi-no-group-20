package domain;

public class Rechazar extends Decision{
    public Rechazar (Atuendo atuendo, Evento evento){
        this.atuendo=atuendo;
    }
    public void deshacer(){
        atuendo.cambiarEstado(Estado.NUEVO);
    }
}
