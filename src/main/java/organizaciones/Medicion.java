package organizaciones;

import java.math.BigDecimal;

public class Medicion {

  private TipoConsumo tipoConsumo;
  private BigDecimal valor;
  private String periodicidad;
  private String periodicidadDeImputacion;

  public Medicion(TipoConsumo tipoConsumo, BigDecimal valor, String periodicidad, String periodicidadDeImputacion) {
    this.tipoConsumo = tipoConsumo;
    this.valor = valor;
    this.periodicidad = periodicidad;
    this.periodicidadDeImputacion = periodicidadDeImputacion;
  }

  public double getHuellaCarbono(TipoConsumo tipo) {
    //validar con tipo consumo?
    return valor.doubleValue();
  }
}
