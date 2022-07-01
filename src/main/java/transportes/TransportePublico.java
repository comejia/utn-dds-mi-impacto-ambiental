package transportes;
import java.util.List;
import excepciones.PuntoIncompatibleException;
import organizaciones.FactorEmision;
import trayectos.Parada;
import trayectos.Punto;

public class TransportePublico implements Transporte {

  private TipoTransportePublico tipoTransportePublico;
  private List<Parada> paradas;
  private int linea;
  private FactorEmision factorEmision;

  public TransportePublico(TipoTransportePublico tipoTransportePublico, List<Parada> paradas, int linea) {
    this.tipoTransportePublico = tipoTransportePublico;
    this.paradas = paradas;
    this.linea = linea;
  }

  public void agregarParada(Parada parada) {
    this.paradas.add(parada);
  }

  public double getDistancia(Punto p1, Punto p2) {
    Parada parada1 = p1.getParada();
    Parada parada2 = p2.getParada();

    if (!paradas.contains(parada1) || !paradas.contains(parada2)) {
      throw new PuntoIncompatibleException("La linea: " + linea + ", no pasa por la/s parada/s indicadas");
    }

    int i1 = paradas.indexOf(parada1), i2 = paradas.indexOf(parada2);

    List<Parada> p = paradas.subList(i1, i2);
    return p.stream().mapToDouble(parada -> parada.getDistanciaProximaParada()).sum();

  }

  @Override
  public FactorEmision getFactorEmision() {
    return this.factorEmision;
  }
}
