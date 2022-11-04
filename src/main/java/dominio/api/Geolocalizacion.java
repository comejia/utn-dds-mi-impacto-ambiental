package dominio.api;

import dominio.trayectos.Direccion;

public interface Geolocalizacion {
  double getDistancia(Direccion direccionInicio, Direccion direccionFin);
}