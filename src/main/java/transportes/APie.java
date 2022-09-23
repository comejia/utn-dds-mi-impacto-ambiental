package transportes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AP")
public class APie extends TransportePrivado {

  public APie() {
    super();
  }

  @Override
  public boolean seComparte() {
    return false;
  }
  
}
