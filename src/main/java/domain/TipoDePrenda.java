package domain;

import java.util.List;

public class TipoDePrenda {
    Categoria categoria;
    List<Material> materialesValidos;

    public TipoDePrenda(Categoria categoria, List<Material> materiales) {
        this.categoria = categoria;
        materialesValidos = materiales;
    }

    public Categoria categoria() {
        return this.categoria;
    }

    public boolean permiteMaterial(Material material) {
        return materialesValidos.contains(material);
    }

    public static final TipoDePrenda ZAPATO = new TipoDePrenda(Categoria.CALZADO, [Material.CUERO]);
    public static final TipoDePrenda REMERA = new TipoDePrenda(Categoria.PARTE_SUPERIOR, [Material.ALGODON]);
    public static final TipoDePrenda PANTALON = new TipoDePrenda(Categoria.PARTE_INFERIOR, [Material.JEAN]);
    public static final TipoDePrenda SHORT = new TipoDePrenda(Categoria.PARTE_INFERIOR, [Material.ALGODON]);
    public static final TipoDePrenda ZAPATO_DE_TACON = new TipoDePrenda(Categoria.CALZADO, [Material.CUERO]);
}

