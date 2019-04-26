package domain;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import exceptions.AtuendoInvalidoException;

import java.util.List;
import java.util.stream.Collectors;

public class Guardarropas {

    private List<Prenda> prendasSuperiores;
    private List<Prenda> prendasInferiores;
    private List<Prenda> calzados;

    public Guardarropas(List<Prenda> prendasSuperiores, List<Prenda> prendasInferiores, List<Prenda> calzados) {
        //TODO: VALIDAR QUE LAS LISTAS SEAN DE LAS CATEGORIAS CORRESPONDIENTES
        this.prendasSuperiores = prendasSuperiores;
        this.prendasInferiores = prendasInferiores;
        this.calzados = calzados;


    }

    public List<Atuendo> sugerir(){

    	return Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(prendasSuperiores), ImmutableSet.copyOf(prendasInferiores), ImmutableSet.copyOf(calzados)))
                .stream()
                .map(list ->{
                    try{
                        return new Atuendo(list.get(0), list.get(1), list.get(2));
                    }catch (AtuendoInvalidoException e){
                        throw new RuntimeException(e);
                    }
                } )
    			.collect(Collectors.toList());

    }

    public void agregarPrendaSuperior(Prenda prendaSuperior) {
        //TODO: VALIDAR QUE SEA DE LA CATEGORIA CORRESPONDIENTE
        this.prendasSuperiores.add(prendaSuperior);
    }

    public void agregarPrendaInferior(Prenda prendaInferior) {
        //TODO: VALIDAR QUE SEA DE LA CATEGORIA CORRESPONDIENTE
        this.prendasInferiores.add(prendaInferior);
    }

    public void agregarCalzado(Prenda calzado) {
        //TODO: VALIDAR QUE SEA DE LA CATEGORIA CORRESPONDIENTE
        this.calzados.add(calzado);
    }
}
