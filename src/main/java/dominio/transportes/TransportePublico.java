package dominio.transportes;

import java.util.List;

import dominio.excepciones.PuntoIncompatibleException;
import dominio.trayectos.Distancia;
import dominio.trayectos.Punto;
import dominio.organizaciones.FactorEmision;
import dominio.trayectos.Parada;
import lombok.Getter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("PU")
@Getter
public class TransportePublico extends Transporte {

  @Enumerated(EnumType.STRING)
  private TipoTransportePublico tipo;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "Transporte_X_Parada")
  private List<Parada> paradas;

  private int linea;

  @OneToOne(cascade = CascadeType.ALL)
  private FactorEmision factorEmision;

  public TransportePublico() { }

  public TransportePublico(TipoTransportePublico tipoTransportePublico, List<Parada> paradas, int linea, FactorEmision factorEmision) {
    this.tipo = tipoTransportePublico;
    this.paradas = paradas;
    this.linea = linea;
    this.factorEmision = factorEmision;
  }

  public void agregarParada(Parada parada) {
    this.paradas.add(parada);
  }

  public double getDistancia(Punto p1, Punto p2) {
    Parada parada1 = p1.getParada();
    Parada parada2 = p2.getParada();

    if (!paradas.contains(parada1) || !paradas.contains(parada2)) {
      throw new PuntoIncompatibleException("La linea: " + linea + ", no pasa por la/s parada/s indicadas");
    }

    int i1 = paradas.indexOf(parada1), i2 = paradas.indexOf(parada2);

    List<Parada> p = paradas.subList(i1, i2);

    return p.stream().mapToDouble(Parada::getDistanciaProximaParada).sum();

  }

  @Override
  public FactorEmision getFactorEmision() {
    return this.factorEmision;
  }

  @Override
  public boolean seComparte() {
    return false;
  }

  @Override
  public String toString() {
    return tipo + " LINEA " + linea;
  }
}
