package transportes;
import API.GeoddsInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import API.Geodds;
import trayectos.Punto;

public abstract class TransportePrivado implements Transporte {
  GeoddsInterface appi;

  public TransportePrivado() {
    this.appi = new Geodds();
  }

  @Override
  public double getDistancia(Punto puntoInicio, Punto puntoFin) {
      try {
        return appi.getDistancia(puntoInicio.getDireccion(), puntoFin.getDireccion());
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
      return 0;
  }

  public void setAppi(GeoddsInterface appi) {
    this.appi = appi;
  }
}
