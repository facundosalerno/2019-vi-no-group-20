package domain.guardarropas;


import domain.prenda.Categoria;
import domain.prenda.Prenda;
import domain.usuario.TipoDeUsuario;
import exceptions.NoAceptaCantidadPrendasException;
import exceptions.NoPermiteGuardarropaIncompletoException;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Stream;



public class GuardarropasLimitado extends Guardarropas {

    public GuardarropasLimitado (List<Prenda> prendasSuperiores, List<Prenda> prendasInferiores, List<Prenda> calzados, List<Prenda> accesorios) {
        if(prendasSuperiores.isEmpty() || prendasInferiores.isEmpty() || calzados.isEmpty() || accesorios.isEmpty())
            throw new NoPermiteGuardarropaIncompletoException();

        if(prendasSuperiores.size() > cantidadPrendasPermitidas() || prendasInferiores.size() > cantidadPrendasPermitidas() || calzados.size() > cantidadPrendasPermitidas() || accesorios.size() > cantidadPrendasPermitidas())
            throw new NoAceptaCantidadPrendasException();
        prendasCoincidenConCategoria(prendasSuperiores, Categoria.PARTE_SUPERIOR);
        prendasCoincidenConCategoria(prendasInferiores, Categoria.PARTE_INFERIOR);
        prendasCoincidenConCategoria(calzados, Categoria.CALZADO);
        prendasCoincidenConCategoria(accesorios, Categoria.ACCESORIOS);
        this.prendasSuperiores = prendasSuperiores;
        this.prendasInferiores = prendasInferiores;
        this.calzados = calzados;
        this.accesorios= accesorios;


    }

    private static int cantidadPrendasPermitidas(){
        return 4;
    }

    @Override
    public TipoDeUsuario tipoDeUsuarioQueAcepta() {
        return TipoDeUsuario.GRATIS;
    }
}
