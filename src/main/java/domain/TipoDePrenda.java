package domain;

import java.util.List;

public class TipoDePrenda {
    Categoria categoria;
    List<Material> materialesValidos;

    public TipoDePrenda(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria categoria() {
        return this.categoria;
    }

    public boolean permiteMaterial(Material material){
        return materialesValidos.contains(material);
    }
    public static final TipoDePrenda ZAPATO = new TipoDePrenda(Categoria.CALZADO);
    public static final TipoDePrenda REMERA = new TipoDePrenda(Categoria.PARTE_SUPERIOR);
    public static final TipoDePrenda PANTALON = new TipoDePrenda(Categoria.PARTE_INFERIOR);
}
