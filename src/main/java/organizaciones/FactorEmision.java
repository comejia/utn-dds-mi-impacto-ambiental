package organizaciones;

import excepciones.UnidadIncompatibleException;

import java.util.ArrayList;
import java.util.Arrays;

public class FactorEmision {

  private int valor;
  private String unidad;
  private TipoConsumo tipoConsumo;

  public FactorEmision(int valor, String unidad, TipoConsumo tipoConsumo) {
    if(unidad.contains("/")){
      String[] unidades = unidad.split("/");
      ArrayList<String> unidadesArray = new ArrayList<String>(Arrays.asList(unidades));
      if (!unidadesArray.contains(tipoConsumo.getUnidad())) {
        throw new UnidadIncompatibleException("La unidad del FE debe ser compatible con el Tipo de Consumo");
      }
    } else if (!unidad.equals(tipoConsumo.getUnidad())) {
      throw new UnidadIncompatibleException("La unidad del FE debe ser compatible con el Tipo de Consumo");
    }
    this.valor = valor;
    this.unidad = unidad;
    this.tipoConsumo = tipoConsumo;
  }

  public int getValor() {
    return valor;
  }

  public String getUnidad() {
    return unidad;
  }

  public TipoConsumo getTipoConsumo() {
    return tipoConsumo;
  }


}
