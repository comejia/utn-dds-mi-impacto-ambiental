package trayectos;

public class Punto {

  private Direccion direccion;
  private Parada parada;

  public Punto(Direccion direccion) {
    this.direccion = direccion;
  }

  public Punto(Parada parada) {
    this.parada = parada;
  }

  public Direccion getDireccion() {
    return direccion;
  }

  public Parada getParada() {
    return parada;
  }

}