package domain.capaPrenda;

import clima.Clima;
import domain.prenda.Categoria;

public abstract class Capa {
    public abstract Categoria getCategoria();
    public abstract boolean abrigaBien(Clima climaActual);
}
