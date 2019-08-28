package domain.usuario;

import clima.Meteorologo;
import domain.atuendo.Atuendo;
import domain.decision.Aceptar;
import domain.decision.Decision;
import domain.decision.Rechazar;
import domain.evento.Evento;
import domain.guardarropas.Guardarropas;
import exceptions.NoExisteGuardarropasException;
import exceptions.NoHayDecisionesParaDeshacer;
import exceptions.ElGuardarropasNoEsAptoException;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.mockito.cglib.core.Local;
import org.uqbar.commons.model.annotations.Observable;

@Observable /** Necesario para poder usarse con arena */
public class Usuario {

    private List<Evento> eventos=new ArrayList<>();
    private Deque<Decision> decisiones=new ArrayDeque<>();
    private List<Atuendo> atuendosAceptados=new ArrayList<>();
    private List<Atuendo> atuendosRechazados=new ArrayList<>();
    private TipoDeUsuario tipoDeUsuario;
    private List<Guardarropas> guardarropas;





    /** Metodos */

    public Usuario(List<Guardarropas> guardarropas, TipoDeUsuario tipoDeUsuario) {
        this.tipoDeUsuario=tipoDeUsuario;
        if (!guardarropas.stream().allMatch(guardarropa -> guardarropa.tipoDeUsuarioQueAcepta() == tipoDeUsuario))
                  throw new ElGuardarropasNoEsAptoException();
        this.guardarropas = guardarropas;
    }

    public List<Atuendo> obtenerSugerenciasDeTodosSusGuardarropas(Meteorologo meteorologo){
        return guardarropas.stream()
                .flatMap(guardarropas -> guardarropas.sugerirAtuendo(meteorologo).stream())
                .collect(Collectors.toList());
    }

    public List<Atuendo> obtenerSugerencias(int indexGuardarropas, Meteorologo meteorologo){
        if(indexGuardarropas<0 || guardarropas.size() >= indexGuardarropas)
            throw new NoExisteGuardarropasException();
        return guardarropas.get(indexGuardarropas).sugerirAtuendo(meteorologo);
    }

    public void cargarEvento(Evento evento){
        eventos.add(evento);
    }

    public List<Atuendo> recibirSugerenciasEvento(String nombreEvento, LocalDateTime fechaEvento){ //Tambien podriamos haber usado index en la lista
        return eventos.stream().filter(evento -> evento.seLlama(nombreEvento))
                .filter(evento -> evento.esEnLaFecha(fechaEvento))
                .collect(Collectors.toList())
                .get(0).obtenerSugerencias();
    }

    public void deshacerUltimaDecision(){
        if(this.decisiones.isEmpty())
            throw new NoHayDecisionesParaDeshacer();
        decisiones.pop().deshacer();
    }

    public void aceptarSugerencia(Atuendo atuendo){
        this.decisiones.push(new Aceptar(atuendo));
        this.atuendosAceptados.add(atuendo);
    }

    public void rechazarSugerencia(Atuendo atuendo){
        decisiones.push(new Rechazar(atuendo));
        atuendosRechazados.add(atuendo);
    }





    /** Arena */

    private LocalDateTime filtroEventoInicial = LocalDateTime.now();
    private LocalDateTime filtroEventoFinal = LocalDateTime.now();
    private List<Evento> eventosFiltrados = new ArrayList<>();

    public void filtrarEventosEntreRangoDeFechas(){
        eventosFiltrados = eventos.stream().filter(evento -> evento.estaEntre(filtroEventoInicial, filtroEventoFinal)).collect(Collectors.toList());
    }





    /** Getters y setters */

    public void setFiltroEventoInicial(LocalDateTime filtroEventoInicial) {
        this.filtroEventoInicial = filtroEventoInicial;
    }

    public void setFiltroEventoFinal(LocalDateTime filtroEventoFinal) {
        this.filtroEventoFinal = filtroEventoFinal;
    }

    public LocalDateTime getFiltroEventoInicial() {
        return filtroEventoInicial;
    }

    public LocalDateTime getFiltroEventoFinal() {
        return filtroEventoFinal;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<Evento> getEventosFiltrados() {
        return eventosFiltrados;
    }

    public void setEventosFiltrados(List<Evento> eventosFiltrados) {
        this.eventosFiltrados = eventosFiltrados;
    }





    /** Equals y hashCode */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(eventos, usuario.eventos) &&
                Objects.equals(decisiones, usuario.decisiones) &&
                Objects.equals(atuendosAceptados, usuario.atuendosAceptados) &&
                Objects.equals(atuendosRechazados, usuario.atuendosRechazados) &&
                Objects.equals(guardarropas, usuario.guardarropas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventos, decisiones, atuendosAceptados, atuendosRechazados, guardarropas);
    }
}
