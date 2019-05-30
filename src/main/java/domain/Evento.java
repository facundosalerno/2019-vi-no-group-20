package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Evento {
    //Contemplar  la temperatura al momento del evento.
    String nombre;
    LocalDateTime fecha;
    String lugar;
    Usuario usuario;
    List<Atuendo> sugerenciasObtenidas;

    public Evento(String nombre, LocalDateTime fechaYHora, String lugar,Usuario usuario){
        this.nombre = nombre;
        this.fecha = fechaYHora;
        this.lugar=lugar;
        this.usuario=usuario;
    }

    public void generarSugerencias(){

    }
}
