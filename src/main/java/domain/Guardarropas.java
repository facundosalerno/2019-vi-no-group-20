package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Guardarropas {

    List<Prenda> prendasSuperior;
    List<Prenda> prendasInferior;
    List<Prenda> prendasCalzado;

    //va a generar atuendos hasta que encuentre uno valido por defecto el atuendo se crea no valido y luego se lo valida.
    public Atuendo generarSugerencia() {
       /*
    	Atuendo sugerencia;
        do {

            sugerencia = this.combinar(prendasSuperior, prendasInferior, prendasCalzado);

        } while (sugerencia.getValidez());

        return sugerencia;
        */
    	
    }

    public List<Atuendo> combinarPrendas(List<Prenda> prendasSuperior, List<Prenda> prendasInferior, List<Prenda> prendasCalzado) {

        //por ahora agarro el primero de la lista y genero la sugerencia
       // Prenda prendaSuperior = prendasSuperior.stream().findFirst().get();
        //Prenda prendaInferior = prendasSuperior.stream().findFirst().get();
        //Prenda prendaCalzado = prendasSuperior.stream().findFirst().get();

       // Atuendo sugerencia = new Atuendo(prendaSuperior, prendaInferior, prendaCalzado);
       
    	return 	Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(this.prendasSuperior), ImmutableSet.copyOf(this.prendasInferior), ImmutableSet.copyOf(this.prendasCalzado)))
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
