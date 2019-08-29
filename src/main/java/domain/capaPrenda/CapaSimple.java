package domain.capaPrenda;

import clima.Clima;
import domain.atuendo.Estado;
import domain.prenda.Categoria;
import domain.prenda.Prenda;

public class CapaSimple extends Capa {
    private Prenda prenda;

    public CapaSimple(Prenda prenda){
        this.prenda = prenda;
    }





    /** Metodos */

    @Override
    public void cambiarEstado(Estado estado){
        prenda.cambiarEstado(estado);
    }

    @Override
    public boolean capaFueAceptada() {
        return prenda.getEstado() == Estado.ACEPTADO;
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

}
