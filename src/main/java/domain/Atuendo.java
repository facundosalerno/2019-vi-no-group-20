package domain;

import java.util.List;

public class Atuendo {

    Prenda prendaSuperior;
    Prenda prendaInferior;
    Prenda prendaCalzado;
    boolean validez;


    public boolean getValidez() {
        return this.validez;

    }

    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda prendaCalzado) {

        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.prendaCalzado = prendaCalzado;
        this.validez = false;
        this.validarAtuendo(prendaSuperior, prendaInferior, prendaCalzado);
    }

    public void validarAtuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda prendaCalzado) {
        this.validez = true;
    }

}
