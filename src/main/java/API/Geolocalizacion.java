package API;

import com.fasterxml.jackson.core.JsonProcessingException;
import trayectos.Direccion;

public interface Geolocalizacion {
  double getDistancia(Direccion direccionInicio, Direccion direccionFin) throws JsonProcessingException;
}