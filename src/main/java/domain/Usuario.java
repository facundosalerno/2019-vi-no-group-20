package domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Usuario {
    private List<Guardarropas> guardarropas;
    
    public Usuario(List<Guardarropas> guardarropas){

        this.guardarropas = guardarropas;
    }

    public List<Atuendo> obtenerSugerencias(){
        return guardarropas.stream()
                .flatMap(guardarropa -> guardarropa.sugerirAtuendo().stream())
                .collect(Collectors.toList());
    }





    //Equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(guardarropas, usuario.guardarropas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guardarropas);
    }
}
