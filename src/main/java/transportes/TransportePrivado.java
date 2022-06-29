package transportes;
import API.GeoddsInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import API.Geodds;
import trayectos.Punto;

public abstract class TransportePrivado implements Transporte {
  GeoddsInterface api;

  public TransportePrivado() {
    this.api = new Geodds();
  }

  @Override
  public double getDistancia(Punto puntoInicio, Punto puntoFin) {
      try {
        return api.getDistancia(puntoInicio.getDireccion(), puntoFin.getDireccion());
      } catch (JsonProcessingException e) {
        throw new RuntimeException("No se pudo obtener la distancia desde la API");
      }
  }

  public void setApi(GeoddsInterface api) {
    this.api = api;
  }
}
