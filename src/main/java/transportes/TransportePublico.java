package transportes;

public class TransportePublico extends Transporte {

    private TipoTransportePublico tipoTransportePublico;
    private Parada paradaInicio;
    private Parada paradaFin;
    private Integer linea;

    public TransportePublico(TipoTransportePublico tipoTransportePublico, Parada paradaInicio, Parada paradaFin, Integer linea) {
        this.tipoTransportePublico = tipoTransportePublico;
        this.paradaInicio = paradaInicio;
        this.paradaFin = paradaFin;
        this.linea = linea;
    }
}
