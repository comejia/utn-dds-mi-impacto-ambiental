package transportes;
import organizaciones.FactorEmision;
import trayectos.Punto;

public interface Transporte {

  public double getDistancia(Punto puntoInicio, Punto puntoFin);
  
  public FactorEmision getFactorEmision();

}
