package dominio.organizaciones;

import dominio.excepciones.UnidadIncompatibleException;
import dominio.usuarios.EntidadPersistente;

import javax.persistence.*;

@Entity
public class FactorEmision extends EntidadPersistente {

  private int valor;
  private String unidad;

  @OneToOne(cascade = CascadeType.ALL)
  private TipoConsumo tipoConsumo;

  public FactorEmision() {}

  public FactorEmision(int valor, String unidad, TipoConsumo tipoConsumo) {
    validarUnidad(unidad, tipoConsumo);
    this.valor = valor;
    this.unidad = unidad;
    this.tipoConsumo = tipoConsumo;
  }

  public void cambiarValor(int valor, String unidad) {
    validarUnidad(unidad, this.tipoConsumo);
    this.valor = valor;
    this.unidad = unidad;
  }

  private void validarUnidad(String unidad, TipoConsumo tipoConsumo) {
    String[] unidades = unidad.split("/");
    if (!unidades[1].equals(tipoConsumo.getUnidad())) {
      throw new UnidadIncompatibleException("La unidad del FE debe ser compatible con el Tipo de Consumo");
    }
  }

  public int getValor() {
    return this.valor;
  }

  public String getUnidad() {
    return this.unidad;
  }

  public TipoConsumo getTipoConsumo() {
    return this.tipoConsumo;
  }
}
