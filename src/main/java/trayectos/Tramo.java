package trayectos;

import lombok.Data;
import lombok.NoArgsConstructor;
import organizaciones.FactorEmision;
import transportes.Transporte;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Tramo {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;

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

  public Long getId() {
    return id;
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
