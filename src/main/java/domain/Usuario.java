package domain;

import exceptions.NoExisteGuardarropasException;
import exceptions.NoHayDecisionesParaDeshacer;

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

    private List<Guardarropas> guardarropas;
    
    public Usuario(List<Guardarropas> guardarropas){
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

    public void deshacerUltimaDecision(){
        if(decisiones.isEmpty())
            throw new NoHayDecisionesParaDeshacer();
        decisiones.pop().deshacer();
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
