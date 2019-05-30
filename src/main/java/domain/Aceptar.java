package domain;

public class Aceptar extends Decision {

    public Aceptar(Atuendo atuendo, Evento evento){
            this.atuendo=atuendo;
        }
    public void deshacer(){
        atuendo.cambiarEstado(Estado.NUEVO);
    }
}
