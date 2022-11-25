package dominio.transportes;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AP")
@Getter
public class APie extends TransportePrivado {

  private String tipo = "A PIE";

  public APie() {
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
