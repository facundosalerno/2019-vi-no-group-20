package domain.decision;

import domain.atuendo.Atuendo;

import java.util.Objects;

public abstract class Decision {
    Atuendo atuendo;

    public abstract void deshacer();





    /** Equals y hashcode */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Decision)) return false;
        Decision decision = (Decision) o;
        return Objects.equals(atuendo, decision.atuendo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atuendo);
    }
}
