package domain.atuendo;

import clima.Clima;
import domain.capaPrenda.Capa;
import domain.prenda.Categoria;
import exceptions.AtuendoInvalidoException;
import exceptions.NoCumpleRequisitoParaCalificarException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.persistence.*;

import java.util.Objects;

@Entity
public class Atuendo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
   
    @OneToOne
    private Capa capaSuperior;
    @OneToOne
    private Capa capaInferior;
    @OneToOne
    private Capa calzado;
    @OneToOne
    private Capa accesorio;

    @Enumerated
    private Estado estado;

    private int calificacion;

    public Atuendo(){}

    public Atuendo(Capa prendaSuperior, Capa prendaInferior, Capa calzado, Capa accesorio) {
        if (!atuendoEsValido(prendaSuperior, prendaInferior, calzado, accesorio))
            throw new AtuendoInvalidoException();
        this.capaSuperior = prendaSuperior;
        this.capaInferior = prendaInferior;
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
        this.capaSuperior.cambiarEstado(estado);
        this.capaInferior.cambiarEstado(estado);
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
        return !capaSuperior.capaFueAceptada() && !capaInferior.capaFueAceptada() && !calzado.capaFueAceptada() && !accesorio.capaFueAceptada();
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
        return capaSuperior.abrigaBien(climaActual) &&
                capaInferior.abrigaBien(climaActual) &&
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
                Objects.equals(capaSuperior, atuendo.capaSuperior) &&
                Objects.equals(capaInferior, atuendo.capaInferior) &&
                Objects.equals(calzado, atuendo.calzado) &&
                Objects.equals(accesorio, atuendo.accesorio) &&
                estado == atuendo.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capaSuperior, capaInferior, calzado, accesorio, estado, calificacion);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
