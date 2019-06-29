package domain.prenda;

import java.util.Arrays;
import java.util.List;


public class TipoDePrenda {
    private Categoria categoria;
    private List<Material> materialesValidos;
    private TemperaturaMaximaDeUso temperatura;

    public TipoDePrenda(Categoria categoria, List<Material> materiales, TemperaturaMaximaDeUso temperatura) {
        this.categoria = categoria;
        materialesValidos = materiales;
        this.temperatura = temperatura;
    }

    public getNivelDeCapa(){
        return 
    }

    public Categoria categoria() {
        return this.categoria;
    }

    public boolean permiteMaterial(Material material) {
        return materialesValidos.contains(material);
    }

    //Atributos estaticos de prueba, hacer todos los necesarios
    public static final TipoDePrenda ZAPATO = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.CUERO, Material.GAMUZA), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda REMERA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.ALGODON), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda CAMISA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.ALGODON,Material.POLIESTER), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda PANTALON = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda SHORT = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.ALGODON,Material.JEAN), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda BLUSA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.ALGODON), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda ZAPATILLA = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.GAMUZA), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda POLLERA = new TipoDePrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.POLIESTER), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda OJOTAS = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.GOMA), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda ALPARGATAS = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.ALGODON), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda ZAPATO_DE_TACON = new TipoDePrenda(Categoria.CALZADO, Arrays.asList(Material.CUERO), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda PULSERA = new TipoDePrenda(Categoria.ACCESORIOS, Arrays.asList(Material.PLASTICO), new TemperaturaMaximaDeUso(30));
    public static final TipoDePrenda ANTEOJOS = new TipoDePrenda(Categoria.ACCESORIOS, Arrays.asList(Material.PLASTICO), new TemperaturaMaximaDeUso(30));
}