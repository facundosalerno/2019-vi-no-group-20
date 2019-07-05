package domain.guardarropas;

import clima.Clima;
import clima.Meteorologo;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import domain.atuendo.Atuendo;
import domain.capaPrenda.CapaCompuesta;
import domain.capaPrenda.CapaSimple;
import domain.prenda.Categoria;
import domain.prenda.Prenda;
import domain.prenda.TemperaturaMaximaDeUso;
import domain.usuario.TipoDeUsuario;
import exceptions.NoPerteneceALaCategoriaException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Guardarropas {

    protected List<Prenda> prendasSuperiores;
    protected List<Prenda> prendasInferiores;
    protected List<Prenda> calzados;
    protected List<Prenda> accesorios;

    public abstract TipoDeUsuario tipoDeUsuarioQueAcepta();

    public List<Atuendo> sugerirAtuendo(Meteorologo meteorologo){
        Clima climaActual = meteorologo.obtenerClima();

        return Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(generarCapasCompuestas(prendasSuperiores, climaActual)), ImmutableSet.copyOf(generarCapasSimples(prendasInferiores)), ImmutableSet.copyOf(generarCapasSimples(calzados)), ImmutableSet.copyOf(generarCapasSimples(accesorios))))
                .stream()
                .map(list -> new Atuendo(list.get(0), list.get(1), list.get(2), list.get(3)))
                .collect(Collectors.toList());
    }

    private List<CapaCompuesta> generarCapasCompuestas(List<Prenda> prendas, Clima climaActual){
        return Sets.combinations(ImmutableSet.copyOf(prendas), TemperaturaMaximaDeUso.getNivelDeCapa(climaActual).capas())
                .stream()
                .map(set -> new CapaCompuesta(generarCapasSimples(ImmutableList.copyOf(set))))
                .filter(capa -> capa.abrigaBien(climaActual))
                .collect(Collectors.toList());
    }

    private List<CapaSimple> generarCapasSimples(List<Prenda> prendas){
        return prendas.stream().map(prenda -> new CapaSimple(prenda)).collect(Collectors.toList());
    }


    //TODO: El nombre de la excepcion esta bien, pero deberia agregarse otra del estilo GuardarropasInvalidoExcepcion para usar en el constructor del guardarropas
    public void prendasCoincidenConCategoria(List<Prenda> prendas, Categoria categoria){
        if(!prendas.stream().allMatch(prenda -> prenda.esDeCategoria(categoria))){
            throw new NoPerteneceALaCategoriaException();
        }
    }
}
