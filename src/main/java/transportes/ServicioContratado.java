package transportes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("SC")
public class ServicioContratado extends TransportePrivado {

  @Enumerated(EnumType.STRING)
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
