package domain;

import exceptions.NoExisteGuardarropasException;
import exceptions.NoHayDecisionesParaDeshacer;
import exceptions.ElGuardarropasNoEsAptoException;

import java.time.LocalDateTime;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Usuario {
    private List<Evento> eventos;
    private Deque<Decision> decisiones;
    private List<Atuendo> atuendosAceptados;
    private List<Atuendo> atuendosRechazados;
    private TipoDeUsuario tipoDeUsuario;

    private List<Guardarropas> guardarropas;
    
    public Usuario(List<Guardarropas> guardarropas, TipoDeUsuario tipoDeUsuario) {
        this.tipoDeUsuario=tipoDeUsuario;
        if (!guardarropas.stream().allMatch(guardarropa -> guardarropa.tipoDeUsuarioQueAcepta() == tipoDeUsuario))
                  throw new ElGuardarropasNoEsAptoException();
        this.guardarropas = guardarropas;
    }

    public List<Atuendo> obtenerSugerenciasDeTodosSusGuardarropas(){
        return guardarropas.stream()
                .flatMap(guardarropas -> guardarropas.sugerirAtuendo().stream())
                .collect(Collectors.toList());
    }

    public List<Atuendo> obtenerSugerencias(int indexGuardarropas){
        if(indexGuardarropas<0 || guardarropas.size() >= indexGuardarropas)
            throw new NoExisteGuardarropasException();
        return guardarropas.get(indexGuardarropas).sugerirAtuendo();
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
        if(decisiones.isEmpty())
            throw new NoHayDecisionesParaDeshacer();
        decisiones.pop().deshacer();
    }

    public void aceptarSugerencia(Atuendo atuendo){
        decisiones.push(new Aceptar(atuendo));
        atuendosAceptados.add(atuendo);
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
