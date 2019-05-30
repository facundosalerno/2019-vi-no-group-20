package domain;


import exceptions.NoPermiteGuardarropaIncompletoException;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Stream;



public class GuardarropasLimitado extends Guardarropas {

    private LinkedBlockingDeque<Integer> totalPrendas;


    //revisar, algo esta mal aca
    private setCantidadPrendasAceptadas(int cantidadPrendasAceptadas){
        totalPrendas = new LinkedBlockingDeque<>(cantidadPrendasAceptadas);
    }


    @Override
    public GuardarropasLimitado (List<Prenda> prendasSuperiores, List<Prenda> prendasInferiores, List<Prenda> calzados, List<Prenda> accesorios) {
        if(prendasSuperiores.isEmpty() || prendasInferiores.isEmpty() || calzados.isEmpty() || accesorios.isEmpty())
            throw new NoPermiteGuardarropaIncompletoException();
        prendasCoincidenConCategoria(prendasSuperiores, Categoria.PARTE_SUPERIOR);
        prendasCoincidenConCategoria(prendasInferiores, Categoria.PARTE_INFERIOR);
        prendasCoincidenConCategoria(calzados, Categoria.CALZADO);
        prendasCoincidenConCategoria(accesorios, Categoria.ACCESORIOS);

        totalPrendas.Stream.of(prendasInferiores, prendasSuperiores,calzados,accesorios).forEach(totalPrendas::addAll); //aca tira el error propio de linkedBl

        this.prendasSuperiores = prendasSuperiores;
        this.prendasInferiores = prendasInferiores;
        this.calzados = calzados;
        this.accesorios= accesorios;


    }

}
