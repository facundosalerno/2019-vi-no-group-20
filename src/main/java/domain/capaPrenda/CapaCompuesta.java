package domain.capaPrenda;

import clima.Clima;
import domain.prenda.Categoria;
import domain.prenda.Prenda;
import exceptions.NoPerteneceALaCategoriaException;
import exceptions.SeRepiteNivelAbrigoException;
import exceptions.capasPrendasSimplesRequiereNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;


public class CapaCompuesta extends Capa {
    private List<CapaSimple> capasPrendas;

    public CapaCompuesta(List<CapaSimple> capasPrendas){
        if(capasPrendas == null)
            throw new capasPrendasSimplesRequiereNonNull();
        if(capasPrendas.isEmpty())
            throw new capasPrendasSimplesRequiereNonNull();
        this.capasPrendas = capasPrendas;
    }

    @Override
    public Categoria getCategoria(){
        return capasPrendas.get(0).getCategoria();
    }

    @Override
    public boolean abrigaBien(Clima climaActual) {
        if(!coincideLaCategoria(capasPrendas))
            return false;
        if(seRepiteNivelAbrigo(capasPrendas))
            return false;
        return true;
    }

    private boolean coincideLaCategoria(List<CapaSimple> capasPrendas){
        return capasPrendas.stream().allMatch(capa -> capa.getCategoria() == capasPrendas.get(0).getCategoria());
    }

    private boolean seRepiteNivelAbrigo(List<CapaSimple> capasPrendas){
         
        	// listo los distintos y comparo con original
    	
    	return capasPrendas.stream().map(p->p.getNivelDeCapa()).distinct().collect(Collectors.toList()).size() == 
    			capasPrendas.stream().map(p->p.getNivelDeCapa()).collect(Collectors.toList()).size();
        		
    }

    
    private boolean estanOrdenadas(List<CapaSimple> capasPrendas){
       
    	return this.ordenarCapa(capasPrendas)==capasPrendas && this.nivelesContiguos(capasPrendas) ;
    }
    
    
    private List<CapaSimple> ordenarCapa(List<CapaSimple> capasPrendas){
    	return  capasPrendas.stream()
    			.sorted(Comparator.comparing(p->p.getNivelDeCapa().capas()))
    			.collect(Collectors.toList());
    }
    
    
    private boolean nivelesContiguos(List<CapaSimple> capasPrendas) {
    	int tamañoCapas = capasPrendas.stream().collect(Collectors.toList()).size();
    	ArrayList<NivelDeCapa> nivelesDeCapa =(ArrayList<NivelDeCapa>) capasPrendas.stream().map(p->p.getNivelDeCapa()).collect(Collectors.toList());
    	  	
    	for (int i=0; i<tamañoCapas-1; ++i)  {
    		if(nivelesDeCapa.get(i).capas() >= nivelesDeCapa.get(i+1).capas())
    		    return false;
    	}
        return true;
    }
    
}
