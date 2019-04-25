package domain;

import exceptions.*;

import static java.util.Objects.requireNonNull;

public class BorradorPrenda {
    private TipoDePrenda tipoPrenda;
    private Material material;
    private Color colorPrimario;
    private Color colorSecundario;
    private Trama trama = Trama.LISA;

    public BorradorPrenda() {

    }
    public Categoria getCategoria(){
        return this.tipoPrenda.categoria();
    }

    public Material getMaterial(){ return this.material; }

    public TipoDePrenda getTipoPrenda(){
        return this.tipoPrenda;
    }

    public Color getColorPrimario(){
        return this.colorPrimario;
    }

    public Color getColorSecundario(){
        return this.colorSecundario;
    }

    public Trama getTrama(){
        return this.trama;
    }

    public void definirTipo(TipoDePrenda tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
        //requireNonNull(tipo, "tipo de prenda es obligatorio"); EL CHEQUEO SE HACE EN definir o cuando hago la prenda o ambos
    }

    public void definirMaterial(Material material) {
        requireNonNull(tipoPrenda, "Defina antes el tipo de prenda");
        if (!tipoPrenda.permiteMaterial(material))
            throw new NoPermiteMaterialException();
        this.material = material;
    }

    public void definirColorPrimario(Color color) {
        this.colorPrimario = color;
    }

    public void definirColorSecundario(Color color) {
        requireNonNull(colorPrimario, "Defina antes el color primario");
        if (color.esIgual(this.colorPrimario)) {
            throw new NoPermiteSerElMismoColorException();
        }
        this.colorSecundario = color;
    }

    public void definirTrama(Trama trama) {
        this.trama = trama;
    }

    public Prenda crearPrenda() {
        requireNonNull(tipoPrenda, "tipo de prenda es obligatorio");
        requireNonNull(material, "material es obligatorio");
        requireNonNull(colorPrimario, "color es obligatorio");
        requireNonNull(trama, "tipo de prenda es obligatorio");
        return new Prenda(tipoPrenda, colorPrimario, colorSecundario, material, trama);
    }

}
