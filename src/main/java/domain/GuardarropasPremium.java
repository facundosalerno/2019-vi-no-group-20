package domain;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import exceptions.NoPermiteGuardarropaIncompletoException;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GuardarropasPremium extends Guardarropas{


    @Override
    public GuardarropasPremium(List<Prenda> prendasSuperiores, List<Prenda> prendasInferiores, List<Prenda> calzados, List<Prenda> accesorios) {

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



}
