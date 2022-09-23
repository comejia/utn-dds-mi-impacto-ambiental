package transportes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BC")
public class Bicicleta extends TransportePrivado {

  public Bicicleta() {
    super();
  }

  @Override
  public boolean seComparte() {
    return false;
  }
}
