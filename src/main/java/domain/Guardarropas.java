package domain;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import exceptions.NoPerteneceALaCategoriaException;
import exceptions.NoPermiteGuardarropaIncompletoException;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Guardarropas {

    private List<Prenda> prendasSuperiores;
    private List<Prenda> prendasInferiores;
    private List<Prenda> calzados;
    private List<Prenda> accesorios;

    public Guardarropas(List<Prenda> prendasSuperiores, List<Prenda> prendasInferiores, List<Prenda> calzados, List<Prenda> accesorios) {
        //TODO: hay que validar que ninguna este vacia o solo alguna??
        if(prendasSuperiores.isEmpty() || prendasInferiores.isEmpty() || calzados.isEmpty() || accesorios.isEmpty())
            throw new NoPermiteGuardarropaIncompletoException();
        prendasCoincidenConCategoria(prendasSuperiores, Categoria.PARTE_SUPERIOR);
        prendasCoincidenConCategoria(prendasInferiores, Categoria.PARTE_INFERIOR);
        prendasCoincidenConCategoria(calzados, Categoria.CALZADO);
        prendasCoincidenConCategoria(accesorios, Categoria.ACCESORIOS);

        this.prendasSuperiores = prendasSuperiores;
        this.prendasInferiores = prendasInferiores;
        this.calzados = calzados;
        this.accesorios= accesorios;
    }

    public List<Atuendo> sugerirAtuendo(){
        int temperaturaActual = 20; //Simula la temperatura actual mientras la API del clima no este disponible
        int elementosDelGrupo = 2; //Determina la cantidad de prendas superpuestas del atuendo
        int variacionTemperatura = 5; //Determina cuantos grados de diferencia puede haber para que el atuendo sea sugerido. En 0 solamente sugeririamos atuendos para la temperatura ambiente actual sin flexibilidad.

        return Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(prendasInferiores), ImmutableSet.copyOf(accesorios), ImmutableSet.copyOf(calzados)))
                .stream()
                .map(list -> new Atuendo(this.obtenerSugerenciasPrendasSuperiores(temperaturaActual, elementosDelGrupo, variacionTemperatura), list.get(0), list.get(1), list.get(2)))
                .collect(Collectors.toList());
    }


    private List<Prenda> obtenerSugerenciasPrendasSuperiores(int temperaturaActual, int elementosDelGrupo, int variacionTemperatura){
        Collections.shuffle(prendasSuperiores);
        return Sets.combinations(ImmutableSet.copyOf(prendasSuperiores.stream().filter(prenda -> prenda.getTipoPrenda().esAptaParaTemperatura(temperaturaActual)).collect(Collectors.toSet())), elementosDelGrupo) //Hace la combinatoria de la prendas superiores filtradas.
                .stream()
                .filter(subgrupoDeNelementos -> prendasAbriganBien(subgrupoDeNelementos, temperaturaActual, variacionTemperatura))
                .collect(Collectors.toList())
                .get(0)
                .stream()
                .collect(Collectors.toList());
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
