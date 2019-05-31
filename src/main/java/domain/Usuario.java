package domain;

import clima.Meteorologo;
import exceptions.NoExisteGuardarropasException;
import exceptions.NoHayDecisionesParaDeshacer;
import exceptions.ElGuardarropasNoEsAptoException;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Usuario {
    private List<Evento> eventos=new ArrayList<>();
    private Deque<Decision> decisiones=new ArrayDeque<>();
    private List<Atuendo> atuendosAceptados=new ArrayList<>();
    private List<Atuendo> atuendosRechazados=new ArrayList<>();
    private TipoDeUsuario tipoDeUsuario;
    private List<Guardarropas> guardarropas;
    
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

    public void cargarEvento(String nombreEvento, LocalDateTime fechaYHora, String lugar){
        eventos.add(new Evento(nombreEvento, fechaYHora,lugar, this));
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



    //Equals y hashCode

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
