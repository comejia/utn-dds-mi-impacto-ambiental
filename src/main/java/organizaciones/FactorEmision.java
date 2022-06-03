package organizaciones;

import exepciones.UnidadIncompatibleException;

public class FactorEmision {

  private int valor;
  private String unidad;
  private TipoConsumo tipoConsumo;

  public FactorEmision(int valor, String unidad, TipoConsumo tipoConsumo) {
    if (!unidad.contains(tipoConsumo.getUnidad())) {
      throw new UnidadIncompatibleException("La unidad del FE debe ser compatible con el Tipo de Consumo");
    }
    this.valor = valor;
    this.unidad = unidad;
    this.tipoConsumo = tipoConsumo;
  }

}
