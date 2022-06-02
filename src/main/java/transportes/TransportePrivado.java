package transportes;

import API.Geodds;
import trayectos.Distancia;
import trayectos.Punto;

public abstract class TransportePrivado implements Transporte {
  Geodds appi;

  public TransportePrivado() {
    this.appi = new Geodds();
  }

  @Override
  public double getDistancia(Punto puntoInicio, Punto puntoFin) {
      return appi.getDistancia(puntoInicio.getDireccion(), puntoFin.getDireccion());
  }
}
