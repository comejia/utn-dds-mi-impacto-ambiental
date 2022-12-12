package dominio.transportes;

import dominio.api.Geodds;
import dominio.api.Geolocalizacion;
import dominio.organizaciones.TipoConsumo;
import dominio.trayectos.Distancia;
import dominio.trayectos.Punto;
import dominio.organizaciones.FactorEmision;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public abstract class TransportePrivado extends Transporte {
  @Transient
  Geolocalizacion api;

  @OneToOne(cascade = CascadeType.ALL)
  protected FactorEmision factorEmision;

  public TransportePrivado() {
    this.api = new Geodds();
    this.factorEmision = new FactorEmision(100,"kgCO2eq/km",new TipoConsumo("Nafta","km","",2));
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
