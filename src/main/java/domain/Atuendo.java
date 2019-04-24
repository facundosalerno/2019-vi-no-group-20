package domain;

import exceptions.AtuendoInvalidoException;


public class Atuendo {

    Prenda prendaSuperior;
    Prenda prendaInferior;
    Prenda calzado;
    //boolean validez;
/*
    public boolean getValidez() {
        return this.validez;

    }
*/
    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado) throws AtuendoInvalidoException {
        validarAtuendo(prendaSuperior, prendaInferior, calzado);
        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
        //this.validez = false;

    }

    public void validarAtuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado) throws AtuendoInvalidoException {
        if((prendaSuperior.getCategoria()!= Categoria.PARTE_SUPERIOR) || (prendaInferior.getCategoria()!= Categoria.PARTE_INFERIOR)|| (calzado.getCategoria() != Categoria.CALZADO))
            throw new AtuendoInvalidoException();
        //this.validez = true;
    }

}
