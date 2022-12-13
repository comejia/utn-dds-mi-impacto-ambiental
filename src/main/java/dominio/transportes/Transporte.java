package dominio.transportes;

import dominio.trayectos.Distancia;
import dominio.trayectos.Punto;
import dominio.organizaciones.FactorEmision;
import dominio.usuarios.EntidadPersistente;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_transporte" , length = 2)
public abstract class Transporte extends EntidadPersistente {

  public abstract double getDistancia(Punto puntoInicio, Punto puntoFin);

  public abstract FactorEmision getFactorEmision();

  public abstract boolean seComparte();

}
