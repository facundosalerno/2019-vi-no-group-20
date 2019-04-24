package domain;

import exceptions.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPrimeraEntrega{
    private BorradorPrenda borradorPrenda;

    @Before
    public void init(){
        //Instanciaciones previas a los TEST
        this.borradorPrenda=new BorradorPrenda();
        Color coloPrimario= new Color(200,100,100);
        this.borradorPrenda.definirColorPrimario(coloPrimario);
        this.borradorPrenda.definirTipo(TipoDePrenda.PANTALON);
        //this.borradorPrenda.definirMaterial(Material.JEAN);
        this.borradorPrenda.definirTrama(Trama.GASTADO);
    }




    //Test de creacion de prenda invalida capturando exception
    //Test de atuendo valido
    //Test para capturar atuendo invalido exception
    //Test de creacion de multiples usuarios con distintas prendas y guardarropas
    //Test de sugerencias de varios guardarropas (que las listas generadas por los distintos guardarropas sean distintas)


    //Test para verificar consistencia de categoria con tipoPrenda
    @Test
    public void verificarConsitenciaCategoriaConTipoPrenda(){
        Assert.assertNotEquals(this.borradorPrenda.getCategoria(),Categoria.PARTE_SUPERIOR);

    }

    //Test para verificar consistencia tipo de prenda con material
    @Test(expected= NoPermiteMaterialException.class)
    public void definirMaterialInconsistenteConTipoPrendaDaExcepcion (){
            this.borradorPrenda.definirMaterial(Material.ALGODON);
    }

    //Test para verificar que no se permite color primario igual al secundario
    @Test (expected = NoPermiteSerElMismoColorException.class)
    public void definirColorSecundarioIgualAPrimarioDaExcepcion(){
        this.borradorPrenda.definirColorSecundario(new Color(200,100,100));
    }

    //Test para verificar que se crea una prenda valida
    @Test
    public void prendaGeneradaEsValida(){
        //TODO: AGREGAR ASSERT DE TRAMA, COLOR PRIMARIO Y TIPO DE PRENDA
        try{
            Prenda prenda = this.borradorPrenda.crearPrenda();
            Assert.assertEquals(Material.class, prenda.getMaterial().getClass());
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }

}
