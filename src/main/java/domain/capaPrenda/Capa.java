package domain.capaPrenda;

import clima.Clima;
import domain.atuendo.Estado;
import domain.prenda.Categoria;

import javax.persistence.*;


@DiscriminatorColumn(name="tipoDeCapa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Capa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public abstract Categoria getCategoria();
    public abstract boolean abrigaBien(Clima climaActual);
    public abstract void cambiarEstado(Estado estado);
    public abstract boolean capaFueAceptada();
}
