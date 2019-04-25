package domain;

import exceptions.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestsValidacionPrenda{
    private BorradorPrenda borradorPrenda;
    private Color colorPrimario;

    @Before
    public void init(){
        //Instanciaciones previas a los TEST
        this.borradorPrenda = new BorradorPrenda();
        this.colorPrimario= new Color(200,100,100);
        this.borradorPrenda.definirTipo(TipoDePrenda.PANTALON);
        this.borradorPrenda.definirMaterial(Material.JEAN);
        this.borradorPrenda.definirColorPrimario(colorPrimario);
        this.borradorPrenda.definirTrama(Trama.GASTADO);
    }




    //--CREANDO LA PRENDA--

    //Test para verificar consistencia de categoria con tipoPrenda
    @Test
    public void verificarConsitenciaCategoriaConTipoPrenda(){
        Assert.assertEquals(borradorPrenda.getCategoria(), Categoria.PARTE_INFERIOR);
        Assert.assertEquals(borradorPrenda.getCategoria(), borradorPrenda.getTipoPrenda().categoria());
    }

    //Test para verificar consistencia tipo de prenda con material
    @Test
    public void verificar_consistencia_material_con_tipo_prenda (){
        Assert.assertEquals(borradorPrenda.getMaterial(), Material.JEAN);
        Assert.assertTrue(borradorPrenda.getTipoPrenda().permiteMaterial(borradorPrenda.getMaterial()));
    }

    //Test para verificar que se asigno correctamente un color secundario, distinto al primario
    @Test
    public void definirColorSecundario(){
        this.borradorPrenda.definirColorSecundario(new Color(90,100,10));
        Assert.assertFalse(borradorPrenda.getColorPrimario().esIgual(borradorPrenda.getColorSecundario()));
    }


    //Test para verificar que se creo la prenda. La validez de la misma la verifican los test anteriores
    @Test
    public void prendaGenerada(){
        Prenda prenda = this.borradorPrenda.crearPrenda();
        Assert.assertNotEquals(null, prenda);
        Assert.assertEquals(prenda.getCategoria(), borradorPrenda.getCategoria());
        Assert.assertEquals(prenda.getTipoPrenda(), borradorPrenda.getTipoPrenda());
        Assert.assertEquals(prenda.getMaterial(), borradorPrenda.getMaterial());
        Assert.assertEquals(prenda.getColorPrimario(), borradorPrenda.getColorPrimario());
        Assert.assertEquals(prenda.getColorSecundario(), borradorPrenda.getColorSecundario());
        Assert.assertEquals(prenda.getTrama(), borradorPrenda.getTrama());
    }






    //--CHEQUEANDO EXCEPCIONES--

    //Test para verificar que si agrego un color secundario igual al primario da error
    @Test (expected = NoPermiteSerElMismoColorException.class)
    public void definir_color_secundario_igual_al_primario(){
        this.borradorPrenda.definirColorSecundario(new Color(200,100,100));
    }

    //Antes de agregar un color secundario, tengo que agregar un primario
    @Test (expected = NullPointerException.class)
    public void definir_color_secundario_antes_que_el_primario(){
        this.borradorPrenda.definirColorPrimario(null);
        this.borradorPrenda.definirColorSecundario(new Color(200,100,100));
    }

    //Test para verificar consistencia tipo de prenda con material
    @Test(expected= NoPermiteMaterialException.class)
    public void definirMaterialInconsistenteConTipoPrendaDaExcepcion (){
            this.borradorPrenda.definirMaterial(Material.ALGODON);
    }


}
