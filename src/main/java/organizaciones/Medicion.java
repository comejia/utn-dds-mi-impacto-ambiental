package organizaciones;

import usuarios.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Entity
public class Medicion extends EntidadPersistente {

  @Enumerated(EnumType.STRING)
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
    return valor.doubleValue();
  }
}
