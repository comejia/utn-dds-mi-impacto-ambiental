package transportes;

public class TransportePublico extends Transporte {

    private TipoTransportePublico tipoTransportePublico;
    private String paradaInicio;
    private String paradaFin;
    private Integer linea;

    public TransportePublico(TipoTransportePublico tipoTransportePublico, String paradaInicio, String paradaFin, Integer linea) {
        this.tipoTransportePublico = tipoTransportePublico;
        this.paradaInicio = paradaInicio;
        this.paradaFin = paradaFin;
        this.linea = linea;
    }
}
