package domain;

import exceptions.AtuendoInvalidoException;


public class Atuendo {

    Prenda prendaSuperior;
    Prenda prendaInferior;
    Prenda calzado;

    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado) throws AtuendoInvalidoException {
        if(!atuendoEsValido(prendaSuperior, prendaInferior, calzado))
            throw new AtuendoInvalidoException();
        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
        //this.validez = false;

    }

    public boolean atuendoEsValido(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado) {
        if((prendaSuperior.getCategoria()!= Categoria.PARTE_SUPERIOR) || (prendaInferior.getCategoria()!= Categoria.PARTE_INFERIOR)|| (calzado.getCategoria() != Categoria.CALZADO))
            return false;
        else
            return true;
    }

    public Prenda getPrendaSuperior() {return prendaSuperior;}
    public Prenda getPrendaInferior() {return prendaInferior;}
    public Prenda getCalzado() {return calzado;}

}
