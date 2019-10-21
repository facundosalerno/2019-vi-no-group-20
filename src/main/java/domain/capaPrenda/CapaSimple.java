package domain.capaPrenda;

import clima.Clima;
import domain.atuendo.Estado;
import domain.prenda.Categoria;
import domain.prenda.Prenda;

import javax.persistence.*;
import java.util.Objects;

@DiscriminatorValue("capaSimple")
@Entity
public class CapaSimple extends Capa {

    @OneToOne
    private Prenda prenda;

    public CapaSimple(){}

    public CapaSimple(Prenda prenda){
        this.prenda = prenda;
    }

    /** Metodos */

    @Override
    public void cambiarEstado(Estado estado){
        prenda.cambiarEstado(estado);
    }

    @Override
    public boolean capaEstaEnUso() {
        return prenda.getEstaEnUso();
    }

    @Override
    public void marcarCapaEnUso() {
        prenda.marcarEnUso();
    }

    @Override
    public void marcarCapaSinUso() {
        prenda.marcarSinUso();
    }

    @Override
    public Categoria getCategoria(){
        return prenda.getCategoria();
    }

    @Override
    public boolean abrigaBien(Clima climaActual) {
        return prenda.abrigaBien(climaActual);
    }

    public NivelDeCapa getNivelDeCapa(){
        return prenda.getNivelDeCapa();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CapaSimple)) return false;
        CapaSimple that = (CapaSimple) o;
        return Objects.equals(prenda, that.prenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenda);
    }
}
