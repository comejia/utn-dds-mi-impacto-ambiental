package API;

import trayectos.Direccion;

public interface Geolocalizacion {
  double getDistancia(Direccion direccionInicio, Direccion direccionFin);
}