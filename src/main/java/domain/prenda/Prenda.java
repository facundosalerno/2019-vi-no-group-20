package domain.prenda;

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

    public Prenda(TipoDePrenda tipo, Color colorPrimario, Color colorSecundario, Material material, Trama trama) {
        this.tipoPrenda = tipo;
        this.material = material;
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.trama = trama;
    }

    public void definirImagen(String pathDeArchivo) throws IOException {
        requireNonNull(pathDeArchivo, "El path del archivo no es valido");
        imagen=new Imagen(pathDeArchivo);
    }

    public boolean esAptaParaTemperatura(int temperatura){
        return this.tipoPrenda.esAptaParaTemperatura(temperatura);
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