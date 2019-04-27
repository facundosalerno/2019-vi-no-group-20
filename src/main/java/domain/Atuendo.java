package domain;

import exceptions.AtuendoInvalidoException;

import java.util.Objects;


public class Atuendo {

    private Prenda prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;
    private Prenda accesorio;

    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado, Prenda accesorio){
        if(!atuendoEsValido(prendaSuperior, prendaInferior, calzado, accesorio))
            throw new AtuendoInvalidoException();
        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
        this.accesorio = accesorio;
    }
    
    
     //TODO: la logica de esta funcion es bastante parecida a la que se usa en el guardarropas para decir si una lista de prendas es valida. Revisar eso
    public boolean atuendoEsValido(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
        return (esCategoria(prendaSuperior, Categoria.PARTE_SUPERIOR) && esCategoria(prendaInferior, Categoria.PARTE_INFERIOR)
                && esCategoria(calzado, Categoria.CALZADO) && esCategoria(accesorio,Categoria.ACCESORIOS));

    }

    public boolean esCategoria(Prenda prenda, Categoria categoria){
        return (prenda.getCategoria() == categoria);
    }



    //Getters
    public Prenda getPrendaSuperior() {return prendaSuperior;}
    public Prenda getPrendaInferior() {return prendaInferior;}
    public Prenda getCalzado() {return calzado;}
    public Prenda getAccesorio() {return accesorio;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atuendo atuendo = (Atuendo) o;
        return Objects.equals(prendaSuperior, atuendo.prendaSuperior) &&
                Objects.equals(prendaInferior, atuendo.prendaInferior) &&
                Objects.equals(calzado, atuendo.calzado) &&
                Objects.equals(accesorio, atuendo.accesorio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prendaSuperior, prendaInferior, calzado, accesorio);
    }
}
