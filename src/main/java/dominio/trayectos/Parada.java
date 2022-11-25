package dominio.trayectos;

import dominio.usuarios.EntidadPersistente;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class Parada extends EntidadPersistente {

  private double distanciaProximaParada;

  public Parada(double distanciaProxParada) {
    this.distanciaProximaParada = distanciaProxParada;
  }

  public double getDistanciaProximaParada() {
    return distanciaProximaParada;
  }

}
