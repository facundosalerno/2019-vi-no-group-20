package domain.prenda;

import clima.Clima;
import domain.atuendo.Estado;
import domain.capaPrenda.NivelDeCapa;

import java.io.IOException;
import java.util.Objects;

import javax.persistence.*;
import javax.persistence.Transient;


import static java.util.Objects.requireNonNull;


@Entity
@Table(name = "PRENDAS")
public class Prenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="tipoPrenda_id")
    private TipoDePrenda tipoPrenda;
	@Enumerated(EnumType.STRING)
    private Material material;
	@Embedded
	@Column(name = "colorPrimario")
    //@AttributeOverride()
	private Color colorPrimario;
	
	@Transient
	private Color colorSecundario;
	@Enumerated(EnumType.STRING)
	private Trama trama;
	
	@Transient
	private Imagen imagen;
	@Enumerated(EnumType.STRING)
	private Estado estado;
    

    /** Warning: construir con BorradorPrenda */
    //Solo para que sea compatible con JPA
    protected Prenda() {}; 
    public Prenda(TipoDePrenda tipo, Color colorPrimario, Color colorSecundario, Material material, Trama trama, Imagen imagen) {
        this.tipoPrenda = tipo;
        this.material = material;
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.trama = trama;
        this.imagen=imagen;
    }





    /** Metodos */

    public void cambiarEstado(Estado estado){
        this.estado = estado;
    }

    public boolean esDeCategoria(Categoria categoria){
        return this.getCategoria()==categoria;
    }

    public boolean abrigaBien(Clima climaActual){
        return tipoPrenda.esAptoParaTemperatura(climaActual);
    }





    /** Getters y setters */

    public Categoria getCategoria() {
        return this.tipoPrenda.categoria();
    }
    public Estado getEstado(){
        return estado;
    }
    public TipoDePrenda getTipoPrenda(){
        return this.tipoPrenda;
    }
    public Material getMaterial(){
        return this.material;
    }
    public Color getColorPrimario() { return colorPrimario;}
    public Color getColorSecundario() { return colorSecundario;}
    public Trama getTrama() { return trama;}
    public NivelDeCapa getNivelDeCapa(){return tipoPrenda.getNivelDeCapa();}





    /** Equals y hashCode */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prenda)) return false;
        Prenda prenda = (Prenda) o;
        return Objects.equals(id, prenda.id) &&
                Objects.equals(tipoPrenda, prenda.tipoPrenda) &&
                material == prenda.material &&
                Objects.equals(colorPrimario, prenda.colorPrimario) &&
                Objects.equals(colorSecundario, prenda.colorSecundario) &&
                trama == prenda.trama &&
                Objects.equals(imagen, prenda.imagen) &&
                estado == prenda.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoPrenda, material, colorPrimario, colorSecundario, trama, imagen, estado);
    }
}
