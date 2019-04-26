package domain;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import exceptions.AtuendoInvalidoException;
import exceptions.NoPerteneceALaCategoriaException;

import java.util.List;
import java.util.stream.Collectors;

public class Guardarropas {

    private List<Prenda> prendasSuperiores;
    private List<Prenda> prendasInferiores;
    private List<Prenda> calzados;

    public Guardarropas(List<Prenda> prendasSuperiores, List<Prenda> prendasInferiores, List<Prenda> calzados) {
        validarPrendasParaGuardarropas(prendasSuperiores,Categoria.PARTE_SUPERIOR);
        validarPrendasParaGuardarropas(prendasInferiores,Categoria.PARTE_INFERIOR);
        validarPrendasParaGuardarropas(calzados,Categoria.CALZADO);

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

    /*public void agregarPrendaSuperior(Prenda prendaSuperior) {
        if(prendaSuperior.getCategoria() != Categoria.PARTE_SUPERIOR){
            throw new NoPerteneceALaCategoriaException();
        }
        this.prendasSuperiores.add(prendaSuperior);
    }

    public void agregarPrendaInferior(Prenda prendaInferior) {
        if(prendaInferior.getCategoria() != Categoria.PARTE_SUPERIOR){
            throw new NoPerteneceALaCategoriaException();
        }
        this.prendasInferiores.add(prendaInferior);
    }

    public void agregarCalzado(Prenda calzado) {
        if(calzado.getCategoria() != Categoria.PARTE_SUPERIOR){
            throw new NoPerteneceALaCategoriaException();
        }
        this.calzados.add(calzado);
    }*/

    public void validarPrendasParaGuardarropas(List<Prenda> prendas, Categoria categoria){
        if(!prendas.stream().allMatch(prenda -> prenda.getCategoria()==categoria)){
            throw new NoPerteneceALaCategoriaException();
        }
    }
}
