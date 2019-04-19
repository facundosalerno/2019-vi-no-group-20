package domain;

public class Prenda {
    private TipoDePrenda tipoPrenda;
    private Material material;
    private Color colorPrimario;
    private Color colorSecundario;
    private Trama trama;


    public Prenda(TipoDePrenda tipo, Color colorPrimario, Color colorSecundario, Material material, Trama trama) {
        this.tipoPrenda = tipo;
        this.material = material;
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.trama = trama;
    }

    public Categoria getCategoria() {
        return this.tipoPrenda.categoria;
    }

    public TipoDePrenda getTipoPrenda(){
        return this.tipoPrenda;
    }
}