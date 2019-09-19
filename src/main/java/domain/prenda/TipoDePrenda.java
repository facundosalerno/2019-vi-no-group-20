package domain.prenda;

import clima.Clima;
import domain.capaPrenda.NivelDeCapa;
import domain.temperaturaPrenda.CualquierTemperatura;
import domain.temperaturaPrenda.RangoTemperatura;
import domain.temperaturaPrenda.TemperaturaPrenda;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class TipoDePrenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tipoPrenda_id;
	@Enumerated(EnumType.STRING)
    private Categoria categoria;


	//TODO: VERIFICAR
	@ElementCollection
	@CollectionTable(name = "MATERIALES_VALIDOS", joinColumns = @JoinColumn(name = "tipoPrenda_id"))
    @Column(name = "material",columnDefinition="VARCHAR(40)")
    @Enumerated(EnumType.STRING)
    private List<Material> materialesValidos;

	@Transient
    private TemperaturaPrenda temperaturaSoportada;

	@Enumerated(EnumType.STRING)
    private NivelDeCapa nivelDeCapa;

	//Solo para que sea compatible con JPA
    protected TipoDePrenda() {}

    public TipoDePrenda(Categoria categoria, List<Material> materiales, TemperaturaPrenda temperatura, NivelDeCapa nivelDeCapa) {
        this.categoria = categoria;
        this.materialesValidos = materiales;
        this.temperaturaSoportada = temperatura;
        this.nivelDeCapa=nivelDeCapa;
    }





    /** Metodos */

    public NivelDeCapa getNivelDeCapa(){
        return this.nivelDeCapa;
    }

    public Categoria categoria() {
        return this.categoria;
    }

    public boolean permiteMaterial(Material material) {
        return materialesValidos.contains(material);
    }

    public boolean esAptoParaTemperatura(Clima climaActual){
        return temperaturaSoportada.seAdapta(climaActual);
    }





    /** Atributos estaticos de prueba */

    public static final TipoDePrenda SIN_ACCESORIO = new TipoDePrenda(Categoria.ACCESORIOS, Arrays.asList(Material.NINGUNO), new CualquierTemperatura(), NivelDeCapa.ABAJO);
    public static final TipoDePrenda ZAPATO = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.CUERO, Material.GAMUZA), new RangoTemperatura(10, 25), NivelDeCapa.MEDIO);
    public static final TipoDePrenda REMERA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.ALGODON), new CualquierTemperatura(), NivelDeCapa.ABAJO);
    public static final TipoDePrenda CAMISA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.ALGODON,Material.POLIESTER, Material.JEAN,Material.LINO), new RangoTemperatura(1, 30), NivelDeCapa.ABAJO);
    public static final TipoDePrenda PANTALON = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN), new RangoTemperatura(-5, 28), NivelDeCapa.MEDIO);
    public static final TipoDePrenda SHORT = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.ALGODON,Material.JEAN), new RangoTemperatura(20, 40), NivelDeCapa.MEDIO);
    public static final TipoDePrenda BLUSA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.ALGODON), new RangoTemperatura(10, 25), NivelDeCapa.MEDIO);
    public static final TipoDePrenda ZAPATILLA = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.GAMUZA), new CualquierTemperatura(), NivelDeCapa.MEDIO);
    public static final TipoDePrenda POLLERA = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.POLIESTER), new RangoTemperatura(20, 30), NivelDeCapa.MEDIO);
    public static final TipoDePrenda OJOTAS = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.GOMA), new RangoTemperatura(25, 40), NivelDeCapa.ABAJO);
    public static final TipoDePrenda ALPARGATAS = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.ALGODON), new RangoTemperatura(15, 30), NivelDeCapa.MEDIO);
    public static final TipoDePrenda PULSERA = new TipoDePrenda(Categoria.ACCESORIOS, Arrays.asList(Material.PLASTICO), new CualquierTemperatura(), NivelDeCapa.ABAJO);
    public static final TipoDePrenda ANTEOJOS = new TipoDePrenda(Categoria.ACCESORIOS, Arrays.asList(Material.PLASTICO), new CualquierTemperatura(), NivelDeCapa.ABAJO);
    public static final TipoDePrenda BUSO = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.ALGODON), new RangoTemperatura(5, 25), NivelDeCapa.MEDIO);
    public static final TipoDePrenda SWEATER = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.LINO), new RangoTemperatura(5, 25), NivelDeCapa.MEDIO);
    public static final TipoDePrenda CAMPERA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.GABARDINA,Material.JEAN, Material.ALGODON, Material.PLUMA), new RangoTemperatura(5, 25), NivelDeCapa.ARRIBA);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoDePrenda)) return false;
        TipoDePrenda that = (TipoDePrenda) o;
        return categoria == that.categoria &&
                Objects.equals(materialesValidos, that.materialesValidos) &&
                Objects.equals(temperaturaSoportada, that.temperaturaSoportada) &&
                nivelDeCapa == that.nivelDeCapa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoria, materialesValidos, temperaturaSoportada, nivelDeCapa);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}