package organizaciones;

import excepciones.UnidadIncompatibleException;

public class FactorEmision {

  private int valor;
  private String unidad;
  private TipoConsumo tipoConsumo;

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
}
