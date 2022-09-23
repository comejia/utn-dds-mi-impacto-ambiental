package transportes;

import API.Geolocalizacion;
import organizaciones.FactorEmision;

import API.Geodds;
import organizaciones.SectorTerritorial;
import sun.util.resources.Bundles;
import trayectos.Punto;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public abstract class TransportePrivado extends Transporte {
  Geolocalizacion api;

  @OneToOne(cascade = CascadeType.ALL)
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
