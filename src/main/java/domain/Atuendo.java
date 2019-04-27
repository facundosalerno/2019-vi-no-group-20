package domain;

import exceptions.AtuendoInvalidoException;

import java.util.Objects;


public class Atuendo {

    private Prenda prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;

    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado){
        if(!atuendoEsValido(prendaSuperior, prendaInferior, calzado))
            throw new AtuendoInvalidoException();
        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
    }

    //TODO: la logica de esta funcion es bastante parecida a la que se usa en el guardarropas para decir si una lista de prendas es valida. Revisar eso
    public boolean atuendoEsValido(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado) {
        return  ((prendaSuperior.getCategoria() == Categoria.PARTE_SUPERIOR) &&
                (prendaInferior.getCategoria()== Categoria.PARTE_INFERIOR) &&
                (calzado.getCategoria() == Categoria.CALZADO));
    }





    //Getters
    public Prenda getPrendaSuperior() {return prendaSuperior;}
    public Prenda getPrendaInferior() {return prendaInferior;}
    public Prenda getCalzado() {return calzado;}





    //Redefinicion de equals y hasCode para poder comparar dos instancias de este objeto distintas.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atuendo atuendo = (Atuendo) o;
        return Objects.equals(prendaSuperior, atuendo.prendaSuperior) &&
                Objects.equals(prendaInferior, atuendo.prendaInferior) &&
                Objects.equals(calzado, atuendo.calzado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prendaSuperior, prendaInferior, calzado);
    }
}
