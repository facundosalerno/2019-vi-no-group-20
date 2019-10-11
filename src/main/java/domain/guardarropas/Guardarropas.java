package domain.guardarropas;

import clima.Clima;
import clima.Meteorologo;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import domain.atuendo.Atuendo;
import domain.capaPrenda.CapasPorTemperatura;
import domain.capaPrenda.CapaCompuesta;
import domain.capaPrenda.CapaSimple;
import domain.prenda.Categoria;
import domain.prenda.Prenda;
import domain.usuario.TipoDeUsuario;
import exceptions.NoPerteneceALaCategoriaException;
import exceptions.NoSePuedenGenerarSugerenciasEx;
import org.apache.commons.collections.ListUtils;

import javax.persistence.*;
import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Entity
@DiscriminatorColumn(name="tipo_guardarropas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Guardarropas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "guardarropas_superiores_id")
	protected List<Prenda> prendasSuperiores = new ArrayList<Prenda>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "guardarropas_inferiores_id")
    protected List<Prenda> prendasInferiores= new ArrayList<Prenda>();;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "guardarropas_calzados_id")
	protected List<Prenda> calzados= new ArrayList<Prenda>();;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "guardarropas_accesorios_id")
    protected List<Prenda> accesorios= new ArrayList<Prenda>();;

    public String getNombre() {
        return nombre;
    }

    String nombre;



    /** Abstract */

    public abstract TipoDeUsuario tipoDeUsuarioQueAcepta();

    /** Metodos */
    
    public List<Prenda> getPrendas(){
        List <Prenda> todasLasPrendas = new ArrayList(prendasSuperiores);
        todasLasPrendas.addAll(prendasInferiores);
        todasLasPrendas.addAll(calzados);
        todasLasPrendas.addAll(accesorios);
        return todasLasPrendas;
    }


    public void prendasCoincidenConCategoria(List<Prenda> prendas, Categoria categoria){
        if(!prendas.stream().allMatch(prenda -> prenda.esDeCategoria(categoria))){
            throw new NoPerteneceALaCategoriaException();
        }
    }

    public List<Atuendo> sugerirAtuendo(Meteorologo meteorologo){
        Clima climaActual = meteorologo.obtenerClima();

        return Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(generarCapasCompuestas(prendasSuperiores, climaActual)), ImmutableSet.copyOf(generarCapasSimples(prendasInferiores, climaActual)), ImmutableSet.copyOf(generarCapasSimples(calzados, climaActual)), ImmutableSet.copyOf(generarCapasSimples(accesorios, climaActual))))
                .stream()
                .map(list -> new Atuendo(list.get(0), list.get(1), list.get(2), list.get(3)))
                .filter(atuendo -> atuendo.esElegible())
                .collect(Collectors.toList());
    }

    private List<CapaCompuesta> generarCapasCompuestas(List<Prenda> prendas, Clima climaActual) {
        int capas = CapasPorTemperatura.capasDeAbrigoParaClima(climaActual);
        if (prendas.size() < capas){ /* Fix para: java.lang.IllegalArgumentException: size (2) must be <= set.size() (1) */
            throw new NoSePuedenGenerarSugerenciasEx("No hay suficientes prendas en el guardarropas para satisfacer el clima actual");
        }

        return Sets.combinations(ImmutableSet.copyOf(prendas), capas)
                .stream()
                .map(set -> new CapaCompuesta(transformarPrendaEnCapa(ImmutableList.copyOf(set))))
                .filter(capa -> capa.abrigaBien(climaActual) && capa.estaBienOrdenada())
                .collect(Collectors.toList());
    }

    private List<CapaSimple> generarCapasSimples(List<Prenda> prendas, Clima climaActual){
        return prendas.stream().filter(prenda -> prenda.abrigaBien(climaActual)).map(prenda -> new CapaSimple(prenda)).collect(Collectors.toList());
    }

    private List<CapaSimple> transformarPrendaEnCapa(List<Prenda> prendas){
        return prendas.stream().map(prenda -> new CapaSimple(prenda)).collect(Collectors.toList());
    }

    public void agregarPrendaSuperior(Prenda prendaAgregada) {
        if (!prendaAgregada.esDeCategoria(Categoria.PARTE_SUPERIOR)){
            throw new NoPerteneceALaCategoriaException();
        }

        this.prendasSuperiores.add(prendaAgregada);

    }

    public void agregarPrendaInferior(Prenda prendaAgregada) {
        if (!prendaAgregada.esDeCategoria(Categoria.PARTE_INFERIOR)){
            throw new NoPerteneceALaCategoriaException();
        }

        this.prendasInferiores.add(prendaAgregada);
    }

    public void agregarPrendaCalzado(Prenda prendaAgregada) {
        if (!prendaAgregada.esDeCategoria(Categoria.CALZADO)){
            throw new NoPerteneceALaCategoriaException();
        }

        this.calzados.add(prendaAgregada);
    }

    public void agregarPrendaAccesorio(Prenda prendaAgregada) {
        if (!prendaAgregada.esDeCategoria(Categoria.ACCESORIOS)){
            throw new NoPerteneceALaCategoriaException();
        }

        this.accesorios.add(prendaAgregada);
    }
    
 // getters y setters
    
	public Long getId() {return id;}
	public List<Prenda> getPrendasSuperiores() {return prendasSuperiores;}
	public List<Prenda> getPrendasInferiores() {return prendasInferiores;}
	public List<Prenda> getCalzados() {return calzados;}
	public List<Prenda> getAccesorios() {return accesorios;}

    /** Equals y hashcode */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guardarropas)) return false;
        Guardarropas that = (Guardarropas) o;
        return Objects.equals(prendasSuperiores, that.prendasSuperiores) &&
                Objects.equals(prendasInferiores, that.prendasInferiores) &&
                Objects.equals(calzados, that.calzados) &&
                Objects.equals(accesorios, that.accesorios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prendasSuperiores, prendasInferiores, calzados, accesorios);
    }
}
