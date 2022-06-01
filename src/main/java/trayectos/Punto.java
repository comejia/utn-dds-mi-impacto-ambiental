package trayectos;

import API.Geodds;

public class Punto {

  private int localidad;
  private String calle;
  private String altura;

  public Punto(int localidad, String calle, String altura) {
    this.localidad = localidad;
    this.calle = calle;
    this.altura = altura;
  }
  public int getLocalidad() {
    return localidad;
  }

  public String getAltura() {
    return altura;
  }

  public String getCalle() {
    return calle;
  }
}
