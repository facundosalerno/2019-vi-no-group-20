package domain.capaPrenda;

import clima.Clima;
import domain.atuendo.Estado;
import domain.prenda.Categoria;

public abstract class Capa {
    public abstract Categoria getCategoria();
    public abstract boolean abrigaBien(Clima climaActual);
    public abstract void cambiarEstado(Estado estado);
    public abstract boolean capaFueAceptada();
}
