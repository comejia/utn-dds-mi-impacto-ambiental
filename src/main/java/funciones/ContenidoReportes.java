package funciones;

public class ContenidoReportes {
  private String cabecera;
  private double valor;

  public ContenidoReportes(String cabecera, double valor) {
    this.cabecera = cabecera;
    this.valor = valor;
  }

  public String getCabecera() {
    return cabecera;
  }

  public double getValor() {
    return valor;
  }
}
