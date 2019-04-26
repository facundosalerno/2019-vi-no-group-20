package domain;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private List<Guardarropas> guardarropas;
    
    public Usuario(List<Guardarropas> guardarropas){

        this.guardarropas = guardarropas;
    }

    public List<Atuendo> obtenerSugerencias(){
        List<Atuendo> aux = new ArrayList<Atuendo>();
        guardarropas.stream()
                .forEach(guardarropa -> aux.addAll(guardarropa.sugerir()));
        return aux;
    }
}
