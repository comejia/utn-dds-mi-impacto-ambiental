package dominio.trayectos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import dominio.organizaciones.FactorEmision;
import dominio.transportes.Transporte;
import dominio.usuarios.EntidadPersistente;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Tramo extends EntidadPersistente {

  @OneToOne(cascade = CascadeType.ALL)
  private Transporte transporte;

  @OneToOne(cascade = CascadeType.ALL)
  private Punto puntoInicio;

  @OneToOne(cascade = CascadeType.ALL)
  private Punto puntoFinal;

  public Tramo(Transporte transporte, Punto puntoInicio, Punto puntoFinal) {
    this.transporte = transporte;
    this.puntoInicio = puntoInicio;
    this.puntoFinal = puntoFinal;
  }

  public double distancia() {
    return transporte.getDistancia(puntoInicio, puntoFinal);
  }

  public boolean esCompartido() {
    return transporte.seComparte();
  }

  public double getHC(String unidad) {
    FactorEmision fe = this.transporte.getFactorEmision();
    return this.distancia() * fe.getValor();
  }
}
