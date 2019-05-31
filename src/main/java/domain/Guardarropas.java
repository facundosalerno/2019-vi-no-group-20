package domain;


import clima.Clima;
import clima.Meteorologo;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import exceptions.NoPerteneceALaCategoriaException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Guardarropas {

    protected List<Prenda> prendasSuperiores;
    protected List<Prenda> prendasInferiores;
    protected List<Prenda> calzados;
    protected List<Prenda> accesorios;



    public List<Atuendo> sugerirAtuendo(Meteorologo meteorologo){
        Clima climaActual = meteorologo.obtenerClima();
        int temperaturaActual = (int) climaActual.getTemperature();
        int elementosDelGrupo = 2; //Determina la cantidad de prendas superpuestas del atuendo
        int variacionTemperatura = 5; //Determina cuantos grados de diferencia puede haber para que el atuendo sea sugerido. En 0 solamente sugeririamos atuendos para la temperatura ambiente actual sin flexibilidad.

        return Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(prendasInferiores.stream().filter(x->x.esAptaParaTemperatura(temperaturaActual)).collect(Collectors.toSet())),
                                                      ImmutableSet.copyOf(accesorios.stream().filter(x->x.esAptaParaTemperatura(temperaturaActual)).collect(Collectors.toSet())),
                                                      ImmutableSet.copyOf(calzados.stream().filter(x->x.esAptaParaTemperatura(temperaturaActual)).collect(Collectors.toSet()))))
                .stream()
                .map(list -> new Atuendo(this.obtenerSugerenciasPrendasSuperiores(temperaturaActual, elementosDelGrupo, variacionTemperatura),
                                         list.get(0),
                                         list.get(1),
                                         list.get(2)))
                .collect(Collectors.toList());
    }

    public abstract TipoDeUsuario tipoDeUsuarioQueAcepta();


    private List<Prenda> obtenerSugerenciasPrendasSuperiores(int temperaturaActual, int elementosDelGrupo, int variacionTemperatura){
        Collections.shuffle(prendasSuperiores);
        return new ArrayList<>(Sets.combinations(ImmutableSet.copyOf(prendasSuperiores.stream().filter(prenda -> prenda.esAptaParaTemperatura(temperaturaActual)).collect(Collectors.toSet())), elementosDelGrupo) //Hace la combinatoria de la prendas superiores filtradas.
                .stream()
                .filter(subgrupoDeNelementos -> prendasAbriganBien(subgrupoDeNelementos, temperaturaActual, variacionTemperatura))
                .collect(Collectors.toList())
                .get(0));
    }





    //TODO: Hacer una algoritmo para cuando la temperatura es menos a 0. Este solo da resultados coherentes con temperaturaActual >= 0
    private boolean prendasAbriganBien(Set<Prenda> prendas, int temperaturaActual, int margenDeFlexibilidad){
        int temperaturaAcorde = prendas.iterator().next().getTipoPrenda().temperaturaResistida();
        while(prendas.iterator().hasNext()){
            temperaturaAcorde -= prendas.iterator().next().getTipoPrenda().temperaturaResistida();
        }
        return temperaturaActual + margenDeFlexibilidad >= Math.abs(temperaturaAcorde) && temperaturaActual - margenDeFlexibilidad <= Math.abs(temperaturaAcorde);
    }



    

    //TODO: El nombre de la excepcion esta bien, pero deberia agregarse otra del estilo GuardarropasInvalidoExcepcion para usar en el constructor del guardarropas
    public void prendasCoincidenConCategoria(List<Prenda> prendas, Categoria categoria){
        if(!prendas.stream().allMatch(prenda -> prenda.getCategoria()==categoria)){
            throw new NoPerteneceALaCategoriaException();
        }
    }
}
