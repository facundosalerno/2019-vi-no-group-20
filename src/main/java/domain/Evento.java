package domain;

import exceptions.NoHaySugerenciasParaElEvento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    public List<Atuendo> obtenerSugerencias(){
        if(sugerenciasObtenidas.isEmpty())
            throw new NoHaySugerenciasParaElEvento();
        return sugerenciasObtenidas;
    }

    public boolean seLlama(String nombre){
        return this.nombre.equals(nombre);
    }

    public boolean esEnLaFecha(LocalDateTime fecha){
        return this.fecha.isEqual(fecha);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento)) return false;
        Evento evento = (Evento) o;
        return diasDeProximidadAEventos == evento.diasDeProximidadAEventos &&
                Objects.equals(nombre, evento.nombre) &&
                Objects.equals(fecha, evento.fecha) &&
                Objects.equals(lugar, evento.lugar) &&
                Objects.equals(usuario, evento.usuario) &&
                Objects.equals(sugerenciasObtenidas, evento.sugerenciasObtenidas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fecha, lugar, usuario, sugerenciasObtenidas, diasDeProximidadAEventos);
    }
}
