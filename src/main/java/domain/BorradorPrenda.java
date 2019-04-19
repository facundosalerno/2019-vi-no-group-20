package domain;

import exceptions.*;
import static java.util.Objects.requireNonNull;

public class BorradorPrenda {
    TipoDePrenda tipoPrenda;
    Material material;
    Color colorPrimario;
    Color colorSecundario;
    Trama trama = Trama.LISA;

    public BorradorPrenda() {

    }

    public void definirTipo(TipoDePrenda tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
        //requireNonNull(tipo, "tipo de prenda es obligatorio"); EL CHEQUEO SE HACE EN definir o cuando hago la prenda o ambos
    }

    public void definirMaterial(Material material) throws NoPermiteMaterialException{
        if(!tipoPrenda.permiteMaterial(material))
            throw new NoPermiteMaterialException("El tipo de prenda" +tipoPrenda+ "no permite el material"+material);
        this.material = material;
    }

    public void definirColorPrimario(Color color) {
        this.colorPrimario = color;
    }

    public void definirColorSecundario(Color color) {
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
