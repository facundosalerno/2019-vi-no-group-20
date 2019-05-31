package domain;

import clima.Meteorologo;
import exceptions.NoHaySugerenciasParaElEvento;
import exceptions.TodaviaNoEstaCercaElEvento;

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

    public Evento(String nombre, LocalDateTime fechaYHora, String lugar, Usuario usuario){
        this.nombre = nombre;
        this.fecha = fechaYHora;
        this.lugar=lugar;
        this.usuario=usuario;
    }

    public void generarSugerencias(Meteorologo meteorologo){
        if(DAYS.between(fecha, LocalDateTime.now()) <= diasDeProximidadAEventos()){ //TODO: hay un problema en esa funcion
            sugerenciasObtenidas = usuario.obtenerSugerenciasDeTodosSusGuardarropas(meteorologo);
        }else{
            throw new TodaviaNoEstaCercaElEvento();
        }
    }

    private int diasDeProximidadAEventos(){
        return 1;
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
        return Objects.equals(nombre, evento.nombre) &&
                Objects.equals(fecha, evento.fecha) &&
                Objects.equals(lugar, evento.lugar) &&
                Objects.equals(usuario, evento.usuario) &&
                Objects.equals(sugerenciasObtenidas, evento.sugerenciasObtenidas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fecha, lugar, usuario, sugerenciasObtenidas);
    }
}
