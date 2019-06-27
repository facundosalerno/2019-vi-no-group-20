package domain.capaPrenda;

import domain.prenda.Categoria;
import domain.prenda.Prenda;
import exceptions.NoPerteneceALaCategoriaException;

import java.util.List;

public class CapaCompuesta extends Capa {
    private Prenda prendaBase;
    private List<CapaSimple> capasPrendas;

    public CapaCompuesta(Prenda prendaBase, List<CapaSimple> capasPrendas){
        if(!coincideLaCategoria(prendaBase, capasPrendas))
            throw new NoPerteneceALaCategoriaException();
        this.prendaBase = prendaBase;
        this.capasPrendas = capasPrendas;
    }

    @Override
    public Categoria getCategoria(){
        return prendaBase.getCategoria();
    }

    private boolean coincideLaCategoria(Prenda prendaBase, List<CapaSimple> capasPrendas){
        return capasPrendas.stream().allMatch(capa -> capa.getCategoria() == prendaBase.getCategoria());
    }
}
