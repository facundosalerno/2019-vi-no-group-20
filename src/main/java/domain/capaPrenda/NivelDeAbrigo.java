package domain.capaPrenda;

public enum NivelDeAbrigo {
    NINGUNO(0),
    LIVIANO(1),
    MODERADO(2),
    ALTO(3),
    MUYALTO(4);

    private int nivel;

    private NivelDeAbrigo(int nivel){
        this.nivel = nivel;
    }

    public int getNivel(){
        return nivel;
    }
}
