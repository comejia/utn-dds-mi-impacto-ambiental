package transportes;

public class ServicioContratado extends Transporte {

    private TipoServicioContratado tipoServicioContratado;

    public ServicioContratado(TipoServicioContratado tipoServicioContratado, String direccionInicio, String direccionLlegada) {
        this.tipoServicioContratado = tipoServicioContratado;
        this.direccionInicio = direccionInicio;
        this.direccionLlegada = direccionLlegada;
    }
}
