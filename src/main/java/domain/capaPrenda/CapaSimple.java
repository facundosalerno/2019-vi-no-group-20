package domain.capaPrenda;

import domain.prenda.Categoria;
import domain.prenda.Prenda;

public class CapaSimple extends Capa {
    private Prenda prenda;

    public CapaSimple(Prenda prenda){
        this.prenda = prenda;
    }

    @Override
    public Categoria getCategoria(){
        return prenda.getCategoria();
    }
}
