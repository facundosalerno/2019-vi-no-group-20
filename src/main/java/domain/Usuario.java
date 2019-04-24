package domain;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private List<Guardarropas> guardarropas;
    //TODO: FALTA DECIDIR SI EL USUARIO LLEVA LISTAS DE PRENDAS (PARTE SUPERIOR, INFERIOR, ETC) O SOLO UN ATRIBUTO POR CADA PRENDA SIMULANDO QUE ESTA VESTIDO

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
