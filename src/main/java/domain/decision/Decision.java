package domain.decision;

import domain.atuendo.Atuendo;

public abstract class Decision {
    Atuendo atuendo;

    public abstract void deshacer();
}
