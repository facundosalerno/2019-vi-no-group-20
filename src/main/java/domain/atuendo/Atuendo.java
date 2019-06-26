package domain.atuendo;

import domain.prenda.Categoria;
import domain.prenda.Prenda;
import exceptions.AtuendoInvalidoException;
import java.util.List;
import java.util.Objects;


public class Atuendo {

    private List<Prenda> prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;
    private Prenda accesorio;
    private Estado estado;

    public Atuendo(List<Prenda> prendaSuperior, Prenda prendaInferior, Prenda calzado, Prenda accesorio){
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
    public boolean atuendoEsValido(List<Prenda> prendaSuperior, Prenda prendaInferior, Prenda calzado, Prenda accesorio) {
        return (esCategoria(prendaSuperior, Categoria.PARTE_SUPERIOR) && esCategoria(prendaInferior, Categoria.PARTE_INFERIOR)
                && esCategoria(calzado, Categoria.CALZADO) && esCategoria(accesorio,Categoria.ACCESORIOS));
        		

    }

    public boolean esCategoria(Prenda prenda, Categoria categoria){
        return (prenda.getCategoria() == categoria);
    }
    public boolean esCategoria(List<Prenda> prendas, Categoria categoria){
        return prendas.stream().allMatch(prenda -> prenda.getCategoria() == categoria);
    }


    //Getters
    public List<Prenda> getPrendaSuperior() {return prendaSuperior;}
    public Prenda getPrendaInferior() {return prendaInferior;}
    public Prenda getCalzado() {return calzado;}
    public Prenda getAccesorio() {return accesorio;}
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
