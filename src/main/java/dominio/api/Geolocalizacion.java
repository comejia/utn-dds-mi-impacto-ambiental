package dominio.api;

import dominio.trayectos.Direccion;
import dominio.trayectos.Distancia;

public interface Geolocalizacion {
  double getDistancia(Direccion direccionInicio, Direccion direccionFin);
}