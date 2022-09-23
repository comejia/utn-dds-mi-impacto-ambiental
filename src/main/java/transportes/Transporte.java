package transportes;

import organizaciones.FactorEmision;
import trayectos.Punto;
import usuarios.EntidadPersistente;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo" , length = 2)
public abstract class Transporte extends EntidadPersistente {

  public abstract double getDistancia(Punto puntoInicio, Punto puntoFin);

  public abstract FactorEmision getFactorEmision();

  public abstract boolean seComparte();

}
