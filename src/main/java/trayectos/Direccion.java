package trayectos;

public class Direccion {
  private String localidad;
  private String calle;
  private String altura;

  public Direccion(String localidad, String calle, String altura) {
    this.localidad = localidad;
    this.calle = calle;
    this.altura = altura;
  }

  public String getLocalidad() {
    return localidad;
  }

  public String getCalle() {
    return calle;
  }

  public String getAltura() {
    return altura;
  }

}
