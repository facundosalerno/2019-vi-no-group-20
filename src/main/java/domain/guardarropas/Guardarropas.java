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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Guardarropas {

    protected List<Prenda> prendasSuperiores;
    protected List<Prenda> prendasInferiores;
    protected List<Prenda> calzados;
    protected List<Prenda> accesorios;





    /** Abstract */

    public abstract TipoDeUsuario tipoDeUsuarioQueAcepta();

    /** Metodos */

    public void prendasCoincidenConCategoria(List<Prenda> prendas, Categoria categoria){
        if(!prendas.stream().allMatch(prenda -> prenda.esDeCategoria(categoria))){
            throw new NoPerteneceALaCategoriaException();
        }
    }

    public List<Atuendo> sugerirAtuendo(Meteorologo meteorologo){
        Clima climaActual = meteorologo.obtenerClima();

        return Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(generarCapasCompuestas(prendasSuperiores, climaActual)), ImmutableSet.copyOf(generarCapasSimples(prendasInferiores)), ImmutableSet.copyOf(generarCapasSimples(calzados)), ImmutableSet.copyOf(generarCapasSimples(accesorios))))
                .stream()
                .map(list -> new Atuendo(list.get(0), list.get(1), list.get(2), list.get(3)))
                .filter(atuendo -> atuendo.esElegible())
                .collect(Collectors.toList());
    }

    private List<CapaCompuesta> generarCapasCompuestas(List<Prenda> prendas, Clima climaActual){
        return Sets.combinations(ImmutableSet.copyOf(prendas), CapasPorTemperatura.capasDeAbrigoParaClima(climaActual))
                .stream()
                .map(set -> new CapaCompuesta(generarCapasSimples(ImmutableList.copyOf(set))))
                .filter(capa -> capa.abrigaBien(climaActual) && capa.estaBienOrdenada())
                .collect(Collectors.toList());
    }

    private List<CapaSimple> generarCapasSimples(List<Prenda> prendas){
        return prendas.stream().map(prenda -> new CapaSimple(prenda)).collect(Collectors.toList());
    }

    public void agregarPrendaSuperior(Prenda prendaAgregada) {
        if (prendaAgregada.getCategoria() != Categoria.PARTE_SUPERIOR){
            throw new NoPerteneceALaCategoriaException();
        }

        this.prendasSuperiores.add(prendaAgregada);
    }

    public void agregarPrendaInferior(Prenda prendaAgregada) {
        if (prendaAgregada.getCategoria() != Categoria.PARTE_INFERIOR){
            throw new NoPerteneceALaCategoriaException();
        }

        this.prendasInferiores.add(prendaAgregada);
    }

    public void agregarPrendaCalzado(Prenda prendaAgregada) {
        if (prendaAgregada.getCategoria() != Categoria.CALZADO){
            throw new NoPerteneceALaCategoriaException();
        }

        this.calzados.add(prendaAgregada);
    }

    public void agregarPrendaAccesorio(Prenda prendaAgregada) {
        if (prendaAgregada.getCategoria() != Categoria.ACCESORIOS){
            throw new NoPerteneceALaCategoriaException();
        }

        this.accesorios.add(prendaAgregada);
    }

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
