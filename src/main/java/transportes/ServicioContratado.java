package transportes;

public class ServicioContratado implements Transporte {

  private TipoServicioContratado tipoServicioContratado;

  public ServicioContratado(TipoServicioContratado tipoServicioContratado) {
    this.tipoServicioContratado = tipoServicioContratado;
  }
}
