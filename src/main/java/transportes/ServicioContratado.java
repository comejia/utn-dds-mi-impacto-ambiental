package transportes;

public class ServicioContratado extends TransportePrivado {

  private TipoServicioContratado tipoServicioContratado;

  public ServicioContratado(TipoServicioContratado tipoServicioContratado) {
    super();
    this.tipoServicioContratado = tipoServicioContratado;
  }

  @Override
  public boolean seComparte() {
    return true;
  }
}
