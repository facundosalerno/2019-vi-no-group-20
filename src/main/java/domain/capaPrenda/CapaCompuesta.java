package domain.capaPrenda;

import clima.Clima;
import com.google.common.collect.Comparators;
import domain.atuendo.Estado;
import domain.prenda.Categoria;
import exceptions.capasPrendasSimplesRequiereNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class CapaCompuesta extends Capa {

    private List<CapaSimple> capasPrendas = new ArrayList<>();

    public CapaCompuesta(List<CapaSimple> capasPrendas){
        if(capasPrendas == null)
            throw new capasPrendasSimplesRequiereNonNull();
        if(capasPrendas.isEmpty())
            throw new capasPrendasSimplesRequiereNonNull();
        this.capasPrendas = capasPrendas;
    }





    /** Metodos */

    @Override
    public Categoria getCategoria(){
        return capasPrendas.get(0).getCategoria();
    }

    @Override
    public boolean abrigaBien(Clima climaActual) {
        return capasPrendas.stream().allMatch(capa -> capa.abrigaBien(climaActual));
    }

    @Override
    public void cambiarEstado(Estado estado) {
        capasPrendas.stream().forEach(capa -> capa.cambiarEstado(estado));
    }

    @Override /* Con que algun elemento de la capa este aceptado entonces no lo podemos usar... es medio una limitacion */
    public boolean capaFueAceptada() {
        return capasPrendas.stream().anyMatch(capa -> capa.capaFueAceptada());
    }

    /* Verifica si la capa compuesta tiene las capas simples bien ordenadas, es decir, segun su NivelDeCapa */
    public boolean estaBienOrdenada() {
        return nivelesDeCapaOrdenadosCoherentemente() && tieneAlMenosUnaParteBaja() && !hayNivelesDuplicados();
    }

    /* Verifica si la capa compuesta tiene las prendas ordenadas por nivel de capa, empezando de la mas baja en adelante */
    private boolean nivelesDeCapaOrdenadosCoherentemente(){
        List<NivelDeCapa> nivelesDeCapas = capasPrendas.stream().map(capaSimple -> capaSimple.getNivelDeCapa()).collect(Collectors.toList());
        return Comparators.isInOrder(nivelesDeCapas, Comparator.<NivelDeCapa> naturalOrder());
    }

    /* Una capa compuesta deberia empezar siempre por una prenda que tenga NivelDeCapa ABAJO */
    private boolean tieneAlMenosUnaParteBaja(){
        return capasPrendas.stream().anyMatch(capa -> capa.getNivelDeCapa() == NivelDeCapa.ABAJO);
    }

    /* Verifica que la capa compuesta no tenga dos niveles de capa repetidos, ejemplo dos remeras */
    private boolean hayNivelesDuplicados(){
        for(int i=0; i < capasPrendas.size()-1 ; ++i){
            if(capasPrendas.get(i) == capasPrendas.get(i+1))
                return true;
        }
        return false;
    }

}
