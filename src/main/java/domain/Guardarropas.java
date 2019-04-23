package domain;


import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Guardarropas {

    List<Prenda> prendasSuperior;
    List<Prenda> prendasInferior;
    List<Prenda> prendasCalzado;

    public List<Atuendo>  generarSugerencias() {
       	
    	return combinarPrendas(prendasSuperior,this.prendasInferior,this.prendasCalzado)
    			.stream()
    			.filter(atuendo-> atuendo.getValidez()==true)
    			.collect(Collectors.toList());
    	
    }

    public List<Atuendo> combinarPrendas(List<Prenda> prendasSuperior, List<Prenda> prendasInferior, List<Prenda> prendasCalzado) {
             
    	return 	Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(prendasSuperior), ImmutableSet.copyOf(prendasInferior), ImmutableSet.copyOf(prendasCalzado)))
        		.stream()
        		.map(list -> new Atuendo(list.get(0), list.get(1), list.get(2)))
        	    .collect(Collectors.toList());
    }

    public Guardarropas(List<Prenda> prendasSuperior, List<Prenda> prendasInferior, List<Prenda> prendasCalzado) {

        this.prendasSuperior = prendasSuperior;
        this.prendasInferior = prendasInferior;
        this.prendasCalzado = prendasCalzado;


    }

    public void agregarPrendaSuperior(Prenda prendaSuperior) {

        this.prendasSuperior.add(prendaSuperior);
    }

    public void agregarPrendaInferior(Prenda prendaInferior) {

        this.prendasSuperior.add(prendaInferior);
    }

    public void agregarPrendaCalzado(Prenda prendaCalzado) {

        this.prendasSuperior.add(prendaCalzado);
    }
}
