package transportes;
import API.Geolocalizacion;
import com.fasterxml.jackson.core.JsonProcessingException;
import API.Geodds;
import trayectos.Punto;

public abstract class TransportePrivado implements Transporte {
  Geolocalizacion api;

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

  public void setApi(Geolocalizacion api) {
    this.api = api;
  }
}
