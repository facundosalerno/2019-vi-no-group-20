package domain.evento;

import clima.Meteorologo;
import domain.atuendo.Atuendo;
import domain.notificaciones.Notificable;
import domain.usuario.Usuario;
import exceptions.NoHaySugerenciasParaElEvento;
import exceptions.TodaviaNoEstaCercaElEvento;
import org.uqbar.commons.model.annotations.Observable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

@Observable
public class Evento implements Notificable {
    //Contemplar  la temperatura al momento del evento.
    private String nombre;
    private LocalDateTime fecha;
    private String lugar;
    private Usuario usuario;
    private FrecuenciaEvento frecuencia;
    private List<Atuendo> sugerenciasObtenidas = new ArrayList<>();
    private boolean existenSugerencias = !sugerenciasObtenidas.isEmpty(); //TODO revisar este cacho de codigo

    /** Warning: crear desde el usuario */
    public Evento(String nombre, LocalDateTime fechaYHora, FrecuenciaEvento frecuencia, String lugar, Usuario usuario){
        this.nombre = nombre;
        this.fecha = fechaYHora;
        this.lugar=lugar;
        this.usuario=usuario;
        this.frecuencia = frecuencia;
    }





    /** Metodos */

    public void generarSugerencias(Meteorologo meteorologo){
        if(Math.abs(DAYS.between(fecha.toLocalDate(), LocalDate.now())) <= diasDeProximidadAEventos()){
            sugerenciasObtenidas = usuario.obtenerSugerenciasDeTodosSusGuardarropas(meteorologo);
        }else{
            throw new TodaviaNoEstaCercaElEvento();
        }
    }

    private int diasDeProximidadAEventos(){
        return 1;
    }

    public List<Atuendo> obtenerSugerencias(){
        if(sugerenciasObtenidas.isEmpty()){
            throw new NoHaySugerenciasParaElEvento();
        }
        return sugerenciasObtenidas;
    }


    public boolean seLlama(String nombre){
        return this.nombre.equals(nombre);
    }

    public boolean esEnLaFecha(LocalDateTime fecha){
        return this.fecha.isEqual(fecha);
    }

    public boolean eshoy(){
        return esEnLaFecha(LocalDateTime.now());
    }

    public boolean estaEntre(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return fecha.isAfter(fechaInicio) && fecha.isBefore(fechaFin);
    }

    public void renovarFrecuencia(){
        frecuencia.renovarEvento(this);
    }





    /** Observer */

    @Override
    public void agregarNotificado(Usuario usuario) {
        /* No hay que agregar nada por que no hay lista de notoficados, solo esta el usuario que creo el evento que quiere ser notificado */
    }

    @Override
    public void eliminarNotificado(Usuario usuario) {
        /*  No hay que agregar nada por que no hay lista de notoficados, solo esta el usuario que creo el evento que quiere ser notificado */
    }

    @Override /* Cuando un evento esta cerca se auto preparan las sugerencias y se le notifica al usuario*/
    public void notificar() {
        usuario.recibirNotificacionEventoCerca(this);
    }





    /** Getters y setters */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public boolean getExistenSugerencias(){
        return existenSugerencias;
    }

    public void setExistenSugerencias(boolean existenSugerencias) {
        this.existenSugerencias = existenSugerencias;
    }





    /** Equals y hashcode */

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
