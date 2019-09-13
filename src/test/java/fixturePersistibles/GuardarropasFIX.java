package fixturePersistibles;


import java.util.ArrayList;
import java.util.Arrays;
import domain.guardarropas.*;
import domain.prenda.*;


public  class  GuardarropasFix {
	 	

	    
	           
	static Color rojo = new Color(255, 0, 0);
	static Color verde = new Color(0, 255, 0);
	static Color azul = new Color(0, 0, 255);

	static Prenda zapatos = armarUnaPrenda(TipoDePrenda.ZAPATO, Material.CUERO, rojo, azul, Trama.GASTADO);
	static Prenda remera = armarUnaPrenda(TipoDePrenda.REMERA, Material.ALGODON, azul, rojo, Trama.CUADROS);
	static Prenda pantalon = armarUnaPrenda(TipoDePrenda.PANTALON, Material.JEAN, verde, rojo, Trama.RAYADA);
    static Prenda shorts = armarUnaPrenda(TipoDePrenda.SHORT, Material.JEAN, verde, rojo, Trama.GASTADO);
    static Prenda blusa = armarUnaPrenda(TipoDePrenda.BLUSA, Material.ALGODON, azul, verde, Trama.LISA);
    static Prenda zapatillas = armarUnaPrenda(TipoDePrenda.ZAPATILLA, Material.GAMUZA, azul, verde, Trama.LISA);
    static Prenda pollera = armarUnaPrenda(TipoDePrenda.POLLERA, Material.POLIESTER, rojo, verde, Trama.CUADROS);
    static Prenda ojotas = armarUnaPrenda(TipoDePrenda.OJOTAS, Material.GOMA, azul, rojo, Trama.LISA);
    static Prenda alpargatas = armarUnaPrenda(TipoDePrenda.ALPARGATAS, Material.ALGODON, azul, rojo, Trama.CUADROS);
    static Prenda camisa = armarUnaPrenda(TipoDePrenda.CAMISA, Material.ALGODON, azul, rojo, Trama.CUADROS);
    static Prenda pulseras=  armarUnaPrenda(TipoDePrenda.PULSERA, Material.PLASTICO, azul, rojo, Trama.LISA);
    static Prenda anteojos = armarUnaPrenda(TipoDePrenda.ANTEOJOS, Material.PLASTICO, rojo, verde, Trama.LISA);
	   
	    
	    public static Prenda armarUnaPrenda(TipoDePrenda tipoDePrenda, Material material, Color colorPrimario, Color colorSecundario, Trama trama){
	        BorradorPrenda borradorPrenda = new BorradorPrenda();
	        borradorPrenda.definirTipo(tipoDePrenda);
	        borradorPrenda.definirMaterial(material);
	        borradorPrenda.definirColorPrimario(colorPrimario);
	        borradorPrenda.definirColorSecundario(colorSecundario);
	        borradorPrenda.definirTrama(trama);
	        return borradorPrenda.crearPrenda();
	    }
	    
	    
	    
	    
	    public   GuardarropasLimitado GuardarropasLimitado() {
	    	return new GuardarropasLimitado(new ArrayList<Prenda>(Arrays.asList(remera, blusa)),new ArrayList<Prenda> (Arrays.asList(shorts, pantalon)),new ArrayList<Prenda>( Arrays.asList(zapatos, zapatillas)),new ArrayList<Prenda>(Arrays.asList(pulseras, anteojos)));
	    }
	    
	    public   GuardarropasPremium GuardarropasPremium() {
	    	return new GuardarropasPremium(new ArrayList<Prenda>(Arrays.asList(remera, blusa)), new ArrayList<Prenda>(Arrays.asList(shorts, pantalon)),new ArrayList<Prenda>( Arrays.asList(zapatos, zapatillas)),new ArrayList<Prenda>(Arrays.asList(pulseras, anteojos)));
	    }


	    
	    
	    
	    public static Prenda getZapatos() {return zapatos;}
	    public static Prenda getRemera() {return remera;}		
	    public static Prenda getPantalon() {return pantalon;}
		public static Prenda getShorts() {return shorts;}
		public static Prenda getBlusa() {return blusa;}		
		public static Prenda getZapatillas() {return zapatillas;}		
		public static Prenda getPollera() {return pollera;}		
		public static Prenda getOjotas() {return ojotas;}
		public static Prenda getAlpargatas() {return alpargatas;}
		public static Prenda getCamisa() {return camisa;}
		public static Prenda getPulseras() {return pulseras;}
		public static Prenda getAnteojos() {return anteojos;}
}
