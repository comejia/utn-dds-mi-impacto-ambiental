package transportes;

import API.Geolocalizacion;
import organizaciones.FactorEmision;

import API.Geodds;
import trayectos.Punto;

public abstract class TransportePrivado implements Transporte {
  Geolocalizacion api;
  protected FactorEmision factorEmision;

  public TransportePrivado() {
    this.api = new Geodds();
  }

  @Override
  public double getDistancia(Punto puntoInicio, Punto puntoFin) {
    return api.getDistancia(puntoInicio.getDireccion(), puntoFin.getDireccion());
  }

  @Override
  public FactorEmision getFactorEmision() {
    return this.factorEmision;
  }

  public void setApi(Geolocalizacion api) {
    this.api = api;
  }
}
