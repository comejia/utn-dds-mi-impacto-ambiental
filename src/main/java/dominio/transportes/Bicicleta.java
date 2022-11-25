package dominio.transportes;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BC")
@Getter
public class Bicicleta extends TransportePrivado {

  private String tipo = "BICICLETA";

  public Bicicleta() {
    super();
  }

  @Override
  public boolean seComparte() {
    return false;
  }

  @Override
  public String toString() {
    return tipo;
  }
}
