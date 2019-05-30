package domain;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Evento {
    //Contemplar  la temperatura al momento del evento.
    private String nombre;
    private LocalDateTime fecha;
    private String lugar;
    private Usuario usuario;
    private List<Atuendo> sugerenciasObtenidas;
    private int diasDeProximidadAEventos = 1;

    public Evento(String nombre, LocalDateTime fechaYHora, String lugar,Usuario usuario){
        this.nombre = nombre;
        this.fecha = fechaYHora;
        this.lugar=lugar;
        this.usuario=usuario;
    }

    public void generarSugerencias(){
        if(DAYS.between(fecha, LocalDateTime.now()) <= diasDeProximidadAEventos){
            sugerenciasObtenidas = usuario.obtenerSugerenciasDeTodosSusGuardarropas();
        }
    }
}
