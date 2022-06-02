package transportes;

import com.fasterxml.jackson.core.JsonProcessingException;

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
    
      try {
        return appi.getDistancia(puntoInicio.getDireccion(), puntoFin.getDireccion());
      } catch (JsonProcessingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return 0;
  }
}
