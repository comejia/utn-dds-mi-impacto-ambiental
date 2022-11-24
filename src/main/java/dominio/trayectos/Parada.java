package dominio.trayectos;

import dominio.usuarios.EntidadPersistente;

import javax.persistence.Entity;

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
