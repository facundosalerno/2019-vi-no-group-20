package domain;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import exceptions.NoPerteneceALaCategoriaException;
import exceptions.NoPermiteGuardarropaIncompletoException;

import java.util.List;
import java.util.stream.Collectors;

public class Guardarropas {

    private List<Prenda> prendasSuperiores;
    private List<Prenda> prendasInferiores;
    private List<Prenda> calzados;
    private List<Prenda> accesorios;

    public Guardarropas(List<Prenda> prendasSuperiores, List<Prenda> prendasInferiores, List<Prenda> calzados, List<Prenda> accesorios) {
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
        //TODO: hacer mas lindo este if
        if(this.prendasSuperiores.size()!=0 && this.prendasInferiores.size()!=0 && this.calzados.size()!= 0 && this.accesorios.size()!=0)
        {
            return Sets.cartesianProduct(ImmutableList.of(ImmutableSet.copyOf(prendasSuperiores), ImmutableSet.copyOf(prendasInferiores), ImmutableSet.copyOf(accesorios), ImmutableSet.copyOf(calzados)))
                    .stream()
                    .map(list -> new Atuendo(list.get(0), list.get(1), list.get(2),list.get(3)))
                    .collect(Collectors.toList());
        }
        throw new NoPermiteGuardarropaIncompletoException();

    }

    //TODO: El nombre de la excepcion esta bien, pero agregarse agregarse otra del estilo GuardarropasInvalidoExcepcion para usar en el constructor del guardarropas
    public void prendasCoincidenConCategoria(List<Prenda> prendas, Categoria categoria){
        if(!prendas.stream().allMatch(prenda -> prenda.getCategoria()==categoria)){
            throw new NoPerteneceALaCategoriaException();
        }
    }
}
