package dominio.api;

import dominio.trayectos.Direccion;
import dominio.trayectos.Distancia;

public interface Geolocalizacion {
  Distancia getDistancia(Direccion direccionInicio, Direccion direccionFin);
}