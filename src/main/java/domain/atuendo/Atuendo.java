package domain.atuendo;

import domain.capaPrenda.Capa;
import domain.prenda.Categoria;
import domain.prenda.Prenda;
import exceptions.AtuendoInvalidoException;
import java.util.List;
import java.util.Objects;


public class Atuendo {

    private Capa prendaSuperior;
    private Capa prendaInferior;
    private Capa calzado;
    private Capa accesorio;
    private Estado estado;

    public Atuendo(Capa prendaSuperior, Capa prendaInferior, Capa calzado, Capa accesorio){
        if(!atuendoEsValido(prendaSuperior, prendaInferior, calzado, accesorio))
            throw new AtuendoInvalidoException();
        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
        this.accesorio = accesorio;
        estado=Estado.NUEVO;
    }



    public void cambiarEstado(Estado estado){
        this.estado=estado;
    }
    
     //TODO: la logica de esta funcion es bastante parecida a la que se usa en el guardarropas para decir si una lista de prendas es valida. Revisar eso
    public boolean atuendoEsValido(Capa prendaSuperior, Capa prendaInferior, Capa calzado, Capa accesorio) {
        return (esCategoria(prendaSuperior, Categoria.PARTE_SUPERIOR) && esCategoria(prendaInferior, Categoria.PARTE_INFERIOR)
                && esCategoria(calzado, Categoria.CALZADO) && esCategoria(accesorio,Categoria.ACCESORIOS));
        		

    }

    public boolean esCategoria(Capa prenda, Categoria categoria){
        return (prenda.getCategoria() == categoria);
    }



    //Getters

    public Estado getEstado(){
        return this.estado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Atuendo)) return false;
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
