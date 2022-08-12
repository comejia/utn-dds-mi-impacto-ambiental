package trayectos;

import organizaciones.FactorEmision;
import transportes.Transporte;

public class Tramo {
  private Transporte transporte;
  private Punto puntoInicio;
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
