package domain;

public class Color {
    int  rojo, verde, azul;
    //podria hacer que el color primario y el secundario sean atributos de color y  los defino con metodos en los que ingreso rojo verde y azul

    public Color (int rojo, int verde, int azul){
        this.rojo=rojo;
        this.verde=verde;
        this.azul=azul;
    }

    public boolean esIgual (Color colorComparado){
        if (colorComparado== null) return false;
        return this.rojo == colorComparado.rojo && this.azul == colorComparado.azul && this.verde == colorComparado.verde;
    }
}
