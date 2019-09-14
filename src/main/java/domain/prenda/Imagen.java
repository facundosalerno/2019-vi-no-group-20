package domain.prenda;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Imagen {
    private int altura=100;
    private int ancho=100;
    BufferedImage imagen;

    Imagen(String nombreDeArchivo) throws IOException {

        BufferedImage imagenDeArchivo = ImageIO.read(new File(nombreDeArchivo));                     //Abre la imagen desde un path

        Image tmp = imagenDeArchivo.getScaledInstance (ancho, altura, Image.SCALE_SMOOTH);         //Crea una instancia de Image con un tamano especifio (100x100)

        BufferedImage imagenNormalizada = new BufferedImage( ancho, altura, BufferedImage.TYPE_INT_ARGB);   //Genera el "lienzo" para poner la imagen normalizada

        Graphics2D g2d = imagenNormalizada.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);        //Dibuja la tmp (imagen con resolucion y tamano normalizado) en imagenNormalizada
        g2d.dispose();                                          //Libera recursos Graphics2D pedidos (g2d)

        imagen = imagenNormalizada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Imagen)) return false;
        Imagen imagen1 = (Imagen) o;
        return altura == imagen1.altura &&
                ancho == imagen1.ancho &&
                Objects.equals(imagen, imagen1.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(altura, ancho, imagen);
    }
}