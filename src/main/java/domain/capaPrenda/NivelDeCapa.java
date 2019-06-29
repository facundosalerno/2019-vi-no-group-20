package domain.capaPrenda;

public enum NivelDeCapa {
    LIVIANO(1),
    MEDIO(2),
    ELEVADO(3),
    MUY_ELEVADO(4);

    private int capas;

    private NivelDeCapa(int capas){
        this.capas = capas;
    }

    public int capas(){
        return capas;
    }
}
