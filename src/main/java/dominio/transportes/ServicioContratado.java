package dominio.transportes;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("SC")
@Getter
public class ServicioContratado extends TransportePrivado {

  @Enumerated(EnumType.STRING)
  private TipoServicioContratado tipo;

  public ServicioContratado() {}

  public ServicioContratado(TipoServicioContratado tipoServicioContratado) {
    super();
    this.tipo = tipoServicioContratado;
  }

  @Override
  public boolean seComparte() {
    return true;
  }

  @Override
  public String toString() {
    return String.valueOf(tipo);
  }
}
