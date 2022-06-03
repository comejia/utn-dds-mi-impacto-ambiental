package transportes;

import com.fasterxml.jackson.core.JsonProcessingException;

import trayectos.Distancia;
import trayectos.Punto;

public interface Transporte {

  public double getDistancia(Punto puntoInicio, Punto puntoFin);

}
