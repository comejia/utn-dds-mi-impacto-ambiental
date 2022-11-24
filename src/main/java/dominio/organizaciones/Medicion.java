package dominio.organizaciones;

import dominio.usuarios.EntidadPersistente;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Medicion extends EntidadPersistente {

  @OneToOne(cascade = CascadeType.ALL)
  private TipoConsumo tipoConsumo;
  private BigDecimal valor;
  private String periodicidad;
  private String periodicidadDeImputacion;

  public Medicion() {}

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
