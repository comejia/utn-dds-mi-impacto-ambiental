package transportes;

import java.util.List;

public class TransportePublico extends Transporte {

  private TipoTransportePublico tipoTransportePublico;
  private List<Parada> paradas;
  private Integer linea;

  public TransportePublico(TipoTransportePublico tipoTransportePublico, List<Parada> paradas, Integer linea) {
    this.tipoTransportePublico = tipoTransportePublico;
    this.paradas = paradas;
    this.linea = linea;
  }

  public void agregarParada(Parada parada) {
    this.paradas.add(parada);
  }
}
