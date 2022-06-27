package organizaciones;

public class Medicion {

  private String tipoConsumo;
  private int valor;
  private String periodicidad;
  private String periodicidadDeImputacion;

  public Medicion(String tipoConsumo, int valor, String periodicidad, String periodicidadDeImputacion) {
    this.tipoConsumo = tipoConsumo;
    this.valor = valor;
    this.periodicidad = periodicidad;
    this.periodicidadDeImputacion = periodicidadDeImputacion;
  }

  public int getHuellaCarbono(TipoConsumo tipo) {
    //validar con tipo consumo?
    return valor;
  }
}
