package API;

import com.fasterxml.jackson.core.JsonProcessingException;
import trayectos.Direccion;

public interface GeoddsInterface {
  double getDistancia(Direccion direccionInicio, Direccion direccionFin) throws JsonProcessingException;
}