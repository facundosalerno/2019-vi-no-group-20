package domain.atuendo;

import clima.Clima;
import domain.capaPrenda.Capa;
import domain.prenda.Categoria;
import domain.prenda.Prenda;
import exceptions.AtuendoInvalidoException;
import exceptions.NoCumpleRequisitoParaCalificarException;

import java.util.List;
import java.util.Objects;


public class Atuendo {

    private Capa prendaSuperior;
    private Capa prendaInferior;
    private Capa calzado;
    private Capa accesorio;
    private Estado estado;
    private int calificacion;

    public Atuendo(Capa prendaSuperior, Capa prendaInferior, Capa calzado, Capa accesorio) {
        if (!atuendoEsValido(prendaSuperior, prendaInferior, calzado, accesorio))
            throw new AtuendoInvalidoException();
        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
        this.accesorio = accesorio;
        estado = Estado.NUEVO;
    }


    /**
     * Metodos
     */

    /* El estado debe cambiar tambien para sus componentes ya que en base a eso, un atuendo podria ser o no elegible */
    public void cambiarEstado(Estado estado) {
        this.estado = estado;
        this.prendaSuperior.cambiarEstado(estado);
        this.prendaInferior.cambiarEstado(estado);
        this.calzado.cambiarEstado(estado);
        this.accesorio.cambiarEstado(estado);
    }

    public boolean atuendoEsValido(Capa prendaSuperior, Capa prendaInferior, Capa calzado, Capa accesorio) {
        return (esCategoria(prendaSuperior, Categoria.PARTE_SUPERIOR) && esCategoria(prendaInferior, Categoria.PARTE_INFERIOR)
                && esCategoria(calzado, Categoria.CALZADO) && esCategoria(accesorio, Categoria.ACCESORIOS));

    }

    /* Si un usuario acepta el atuendo, entonces acepta todas sus prendas (capas) y ya no sera elegible por otro usuario que comparta el mismo guardarropas */
    public boolean esElegible() {
        return estado != Estado.ACEPTADO && ningunaPrendaFueAceptada();
    }

    private boolean ningunaPrendaFueAceptada() {
        return !prendaSuperior.capaFueAceptada() && !prendaInferior.capaFueAceptada() && !calzado.capaFueAceptada() && !accesorio.capaFueAceptada();
    }

    public boolean esCategoria(Capa prenda, Categoria categoria) {
        return (prenda.getCategoria() == categoria);
    }

    public void calificar(int calificacion) {        //Solo se usa al momento de generar una decision de calificar
        if (this.estado == Estado.ACEPTADO) {
            this.calificacion = calificacion > 10 ? 10 : calificacion < 0 ? 0 : calificacion;
        }else{
            throw new NoCumpleRequisitoParaCalificarException();
        }

    }

    public boolean revalidadAtuendo(Clima climaActual){
        return prendaSuperior.abrigaBien(climaActual) &&
                prendaInferior.abrigaBien(climaActual) &&
                calzado.abrigaBien(climaActual) &&
                accesorio.abrigaBien(climaActual);
    }

    /**
     * Getters y setters
     */

    public Estado getEstado() {
        return this.estado;
    }


    /**
     * Equals y hashcode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Atuendo)) return false;
        Atuendo atuendo = (Atuendo) o;
        return calificacion == atuendo.calificacion &&
                Objects.equals(prendaSuperior, atuendo.prendaSuperior) &&
                Objects.equals(prendaInferior, atuendo.prendaInferior) &&
                Objects.equals(calzado, atuendo.calzado) &&
                Objects.equals(accesorio, atuendo.accesorio) &&
                estado == atuendo.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prendaSuperior, prendaInferior, calzado, accesorio, estado, calificacion);
    }



}
