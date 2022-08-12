package transportes;

import organizaciones.FactorEmision;
import trayectos.Punto;

public interface Transporte {

  double getDistancia(Punto puntoInicio, Punto puntoFin);

  FactorEmision getFactorEmision();

  boolean seComparte();

}
