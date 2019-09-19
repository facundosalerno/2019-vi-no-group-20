package domain.temperaturaPrenda;

import clima.Clima;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class TemperaturaPrenda {
    @Id
    Long id;
    public abstract boolean seAdapta(Clima climaActual);
}
