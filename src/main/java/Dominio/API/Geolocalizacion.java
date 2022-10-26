package Dominio.API;

import Dominio.trayectos.Direccion;

public interface Geolocalizacion {
  double getDistancia(Direccion direccionInicio, Direccion direccionFin);
}