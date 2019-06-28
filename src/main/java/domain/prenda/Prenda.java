package domain.prenda;

import domain.capaPrenda.NivelDeAbrigo;

import java.io.IOException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Prenda {
    private TipoDePrenda tipoPrenda;
    private Material material;
    private Color colorPrimario;
    private Color colorSecundario;
    private Trama trama;
    private Imagen imagen;
    private NivelDeAbrigo nivelDeAbrigo;

    public Prenda(TipoDePrenda tipo, Color colorPrimario, Color colorSecundario, Material material, Trama trama, NivelDeAbrigo nivelDeAbrigo) {
        this.tipoPrenda = tipo;
        this.material = material;
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.trama = trama;
        this.nivelDeAbrigo = nivelDeAbrigo;
    }

    public void definirImagen(String pathDeArchivo) throws IOException {
        requireNonNull(pathDeArchivo, "El path del archivo no es valido");
        imagen=new Imagen(pathDeArchivo);
    }

    public int temperaturaResistida(){
        return tipoPrenda.temperaturaResistida();
    }


    //Getters
    public Categoria getCategoria() {
        return this.tipoPrenda.categoria();
    }
    public TipoDePrenda getTipoPrenda(){
        return this.tipoPrenda;
    }
    public Material getMaterial(){
        return this.material;
    }
    public Color getColorPrimario() { return colorPrimario;}
    public Color getColorSecundario() { return colorSecundario;}
    public Trama getTrama() { return trama;}






    //Equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenda prenda = (Prenda) o;
        return Objects.equals(getTipoPrenda(), prenda.getTipoPrenda()) &&
                getMaterial() == prenda.getMaterial() &&
                Objects.equals(getColorPrimario(), prenda.getColorPrimario()) &&
                Objects.equals(getColorSecundario(), prenda.getColorSecundario()) &&
                getTrama() == prenda.getTrama();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTipoPrenda(), getMaterial(), getColorPrimario(), getColorSecundario(), getTrama());
    }
}