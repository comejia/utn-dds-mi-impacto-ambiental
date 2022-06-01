package trayectos;

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
}
