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
        if(!coincideLaCategoria(capasPrendas))
            throw new NoPerteneceALaCategoriaException();
        if(seRepiteNivelAbrigo(capasPrendas))
            throw new SeRepiteNivelAbrigoException();
        this.capasPrendas = capasPrendas;
    }

    @Override
    public Categoria getCategoria(){
        return capasPrendas.get(0).getCategoria();
    }

    @Override
    public boolean abrigaBien(Clima climaActual) {
        return true;
    }

    private boolean coincideLaCategoria(List<CapaSimple> capasPrendas){
        return capasPrendas.stream().allMatch(capa -> capa.getCategoria() == capasPrendas.get(0).getCategoria());
    }

    private boolean seRepiteNivelAbrigo(List<CapaSimple> capasPrendas){
        return true;
    }
}
