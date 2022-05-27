package transportes;

import java.util.List;

public class TransportePublico implements Transporte {

  private TipoTransportePublico tipoTransportePublico;
  private List<Parada> paradas;
  private int linea;

  public TransportePublico(TipoTransportePublico tipoTransportePublico, List<Parada> paradas, int linea) {
    this.tipoTransportePublico = tipoTransportePublico;
    this.paradas = paradas;
    this.linea = linea;
  }

  public void agregarParada(Parada parada) {
    this.paradas.add(parada);
  }
}
