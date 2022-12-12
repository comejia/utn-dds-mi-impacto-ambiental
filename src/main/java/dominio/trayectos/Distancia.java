package dominio.trayectos;

public class Distancia {

  private Double valor;
  private String unidad;

  public Distancia(String unidad, Double valor) {
    this.unidad = unidad;
    this.valor = valor;
  }

  public String getUnidad() {
    return unidad;
  }

  public Double getValor() {
    return valor;
  }

}
