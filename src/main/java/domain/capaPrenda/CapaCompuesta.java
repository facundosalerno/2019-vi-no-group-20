package domain.capaPrenda;

import clima.Clima;
import domain.prenda.Categoria;
import domain.prenda.Prenda;
import exceptions.NoPerteneceALaCategoriaException;
import exceptions.SeRepiteNivelAbrigoException;
import exceptions.capasPrendasSimplesRequiereNonNull;

import java.util.List;

public class CapaCompuesta extends Capa {
    private List<CapaSimple> capasPrendas;

    public CapaCompuesta(List<CapaSimple> capasPrendas){
        if(capasPrendas == null)
            throw new capasPrendasSimplesRequiereNonNull();
        if(capasPrendas.isEmpty())
            throw new capasPrendasSimplesRequiereNonNull();
        this.capasPrendas = capasPrendas;
    }

    @Override
    public Categoria getCategoria(){
        return capasPrendas.get(0).getCategoria();
    }

    @Override
    public boolean abrigaBien(Clima climaActual) {
        if(!coincideLaCategoria(capasPrendas))
            return false;
        if(seRepiteNivelAbrigo(capasPrendas))
            return false;
    }

    private boolean coincideLaCategoria(List<CapaSimple> capasPrendas){
        return capasPrendas.stream().allMatch(capa -> capa.getCategoria() == capasPrendas.get(0).getCategoria());
    }

    private boolean seRepiteNivelAbrigo(List<CapaSimple> capasPrendas){
        return capasPrendas.stream().;
    }

    private boolean estanOrdenadas(CapaSimple capaDeAbajo, CapaSimple capaDeArriba){
        return capaDeAbajo.
    }
}
